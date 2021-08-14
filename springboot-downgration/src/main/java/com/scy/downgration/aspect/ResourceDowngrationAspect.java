package com.scy.downgration.aspect;

import com.google.common.util.concurrent.RateLimiter;
import com.scy.common.utils.SpringContextUtil;
import com.scy.downgration.annoation.ResourceDowngration;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @desc:
 * @program: springboot-configuration
 * @author: Suocaiyuan
 * @date: 2021-08-14 14:45
 **/
@Slf4j
@Aspect
@Component
public class ResourceDowngrationAspect {

    private Map<String, RateLimiter> ratelimiterMap = new ConcurrentHashMap<>();

    private Map<String, Integer> orginMaxThresholdMap = new ConcurrentHashMap<>();

    private Map<String, AtomicInteger> counter = new ConcurrentHashMap<>();

    @Around(value = "@annotation(resourceDowngration)")
    public Object resourceDowngration(ProceedingJoinPoint pjp, ResourceDowngration downgration) throws Throwable {
        return this.downgrationWithRateLimiter(pjp, downgration);
    }

    private Object downgrationWithRateLimiter(ProceedingJoinPoint pjp, ResourceDowngration downgration) throws Throwable {
        String signature = pjp.getSignature().toLongString();
        int maxThreshold = downgration.maxThreshold();

        log.info("maxThreshold -> {}", maxThreshold);
        RateLimiter rateLimiter;
        if (!ratelimiterMap.containsKey(signature)) {
            rateLimiter = RateLimiter.create(maxThreshold);
            ratelimiterMap.put(signature, rateLimiter);
        }

        rateLimiter = this.resetRateLimiter(signature, maxThreshold);

        boolean isAllowPass = rateLimiter.tryAcquire();
        if (isAllowPass) {
            return pjp.proceed();
        }
        return this.fallback(pjp, downgration);
    }

    /**
     * 阈值发生变化，动态切换
     *
     * @param signature
     * @param maxThreshold
     * @return
     */
    private RateLimiter resetRateLimiter(String signature, int maxThreshold) {
        int orginMaxThreshold;
        RateLimiter rateLimiter = ratelimiterMap.get(signature);

        //说明发生阈值发生动态切换，此时则需要变更限流器
        if (orginMaxThresholdMap.containsKey(signature)) {
            orginMaxThreshold = orginMaxThresholdMap.get(signature);
            log.info("signature:{}-->orginMaxThreshold is :{},currentMaxThreshold is :{},rateLimiter maxThreshold will change to -->{}"
                    , signature, orginMaxThreshold, maxThreshold, maxThreshold);
            rateLimiter.setRate(orginMaxThreshold);
        } else {
            orginMaxThresholdMap.put(signature, maxThreshold);
        }
        return rateLimiter;
    }

    /**
     * 限流回退逻辑
     *
     * @param pjp
     * @param downgration
     * @return
     */
    private Object fallback(ProceedingJoinPoint pjp, ResourceDowngration downgration) {
        String methodname = downgration.fallbackMethod();
        if (StringUtils.isEmpty(methodname)) {
            methodname = pjp.getSignature().getName();
        }
        try {
            MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
            Class<?> fallbackClass = downgration.fallbackClass();
            Class[] args = methodSignature.getParameterTypes();
            Method method = SpringContextUtil.getBean(fallbackClass).getClass().getMethod(methodname, args);
            return method.invoke(SpringContextUtil.getBean(fallbackClass), pjp.getArgs());
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Object downgration(ProceedingJoinPoint pjp, ResourceDowngration downgration) throws Throwable {
        String signature = pjp.getSignature().toLongString();
        int maxThreshold = downgration.maxThreshold();
        log.info("maxThreshold-->{}", maxThreshold);

        if (counter.containsKey(signature)) {
            if (counter.get(signature).incrementAndGet() > maxThreshold) {
                log.info("signature:{},current requestCount:{},maxThreshold:{}", signature, counter.get(signature).get(), maxThreshold);
                return this.fallback(pjp, downgration);
            }
        } else {
            counter.put(signature, new AtomicInteger());
        }

        //特殊情况下 maxthreshold为0
        if (counter.get(signature).get() > maxThreshold) {
            log.info("signature:{},current requestCount:{},maxThreshold:{}", signature, counter.get(signature).get(), maxThreshold);
            return this.fallback(pjp, downgration);
        }
        Object result = null;
        try {
            result = pjp.proceed();
        } catch (Throwable throwable) {
            throw throwable;
        } finally {
            counter.get(signature).decrementAndGet();
        }
        return result;
    }

}

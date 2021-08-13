package com.scy.api.aop;

import com.google.common.util.concurrent.RateLimiter;
import com.scy.api.annoation.ResourceLimit;
import com.scy.common.utils.SpringContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.*;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 类名： ResourceLimitAspect <br>
 * 描述：TODO <br>
 * 创建日期： 2021/8/12 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */
@Slf4j
@Aspect
@Component
public class ResourceLimitAspect {

    //限流
    private Map<String, RateLimiter> limitMap = new ConcurrentHashMap<>();

    //存放阈值
    private Map<String, Integer> orginMaxThresholdMap = new ConcurrentHashMap<>();

    //普通计数器限流
    private Map<String, AtomicInteger> counter = new ConcurrentHashMap<>();

    @Around(value = "@annotation(resourceLimit)")
    public Object resourceLimit(ProceedingJoinPoint pjp, ResourceLimit resourceLimit) throws Throwable {
        return this.limitWithRateLimiter(pjp, resourceLimit);
    }


    private Object limitWithRateLimiter(ProceedingJoinPoint pjp, ResourceLimit resourceLimit) throws Throwable {
        var signature = pjp.getSignature().toLongString();
        var maxThreshold = resourceLimit.maxThreshold();
        log.info("maxThreshold -->{}", maxThreshold);

        RateLimiter rateLimiter;
        //permitsPerSecond为每秒往令牌里放入几个令牌
        if (!limitMap.containsKey(signature)) {
            rateLimiter = RateLimiter.create(maxThreshold);
            limitMap.put(signature, rateLimiter);
        }

        rateLimiter = this.resetRateLimiterWithChange(signature, maxThreshold);

        var isAllowPass = rateLimiter.tryAcquire();
        if (isAllowPass) {
            return pjp.proceed();
        }

        return this.fallback(pjp, resourceLimit);
    }

    /**
     * 阈值动态变化，改变限流阈值
     *
     * @param signature
     * @param maxThreshold
     * @return
     */
    private RateLimiter resetRateLimiterWithChange(String signature, int maxThreshold) {
        int orginMaxThreshold;
        var rateLimiter = limitMap.get(signature);
        if (orginMaxThresholdMap.containsKey(signature)) {
            orginMaxThreshold = orginMaxThresholdMap.get(signature);
            if (orginMaxThreshold != maxThreshold) {
                log.info("signature:{}-->orginMaxThreshold is :{},currentMaxThreshold is :{},rateLimiter maxThreshold will change to -->{}"
                        , signature, orginMaxThreshold, maxThreshold, maxThreshold);
            }
            rateLimiter.setRate(maxThreshold);
        } else {
            orginMaxThresholdMap.put(signature, maxThreshold);
        }
        return rateLimiter;
    }


    private Object downgrade(ProceedingJoinPoint pjp, ResourceLimit resourceLimit) throws Throwable {
        String signature = pjp.getSignature().toLongString();
        int maxThreshold = resourceLimit.maxThreshold();
        log.info("maxThreshold --> {}", maxThreshold);
        if (counter.containsKey(signature)) {
            if (counter.get(signature).incrementAndGet() > maxThreshold) {
                log.info("signature:{},currentCount:{},maxThreshold:{}", signature, counter.get(signature), maxThreshold);
                return this.fallback(pjp, resourceLimit);
            }
        } else {
            counter.put(signature, new AtomicInteger(1));
        }

        //如果maxthreshold 为0
        if (counter.get(signature).get() > maxThreshold) {
            log.info("signature:{},currentCount:{},maxThreshold:{}", signature, counter.get(signature), maxThreshold);
            return this.fallback(pjp, resourceLimit);
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

    /**
     * 限流回退逻辑
     **/
    private Object fallback(ProceedingJoinPoint pjp, ResourceLimit resourceLimit) {
        String methodName = resourceLimit.fallbackMethod();
        if (StringUtils.isEmpty(methodName)) {
            methodName = pjp.getSignature().getName();
        }
        try {
            MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
            Method method = SpringContextUtil.getBean(resourceLimit.fallbackClass()).getClass().getMethod(methodName, methodSignature.getParameterTypes());
            log.info("enter fallbackMethod:{}", method);
            return method.invoke(SpringContextUtil.getBean(resourceLimit.fallbackClass()), pjp.getArgs());
        } catch (NoSuchMethodException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            log.error("fallback error:" + e.getMessage(), e);
        }
        return null;
    }

    public void changeThreshold(String resourceId, Integer threshold) {
        ResourceLimit resourceLimit = this.getResourceLimitById(resourceId);
        InvocationHandler invocationHandler = Proxy.getInvocationHandler(resourceLimit);

        try {
            Field field = invocationHandler.getClass().getDeclaredField("memberValues");
            field.setAccessible(true);
            Map<String,Object> memberValues = (Map<String, Object>) field.get(invocationHandler);
            if (memberValues.containsKey(MAX_THRESHOLD_KEY)) {
                memberValues.put(MAX_THRESHOLD_KEY, threshold);
                log.info("resouceId: {} change maxThreshold to {} ok !",resourceId,threshold);
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            log.error("changeThreshold error:"+e.getMessage(),e);
        }
    }


}

package com.scy.downgration.utils;

import com.scy.downgration.annoation.ResourceDowngration;
import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 类名： ResourceDowngrationUtil <br>
 * 描述：TODO <br>
 * 创建日期： 2021/8/12 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */
@Slf4j
public enum ResourceDowngrationUtil {
    INSTANCE;

    public static final String packageName = "com.com.scy.service";

    public static final String MAX_THRESHOLD_KEY = "maxThreshold";

    private Map<String, ResourceDowngration> resourceDowngrationMap = new ConcurrentHashMap<>();

    public void init() {
        Reflections reflections = new Reflections(packageName, new MethodAnnotationsScanner());
        Set<Method> methodSet = reflections.getMethodsAnnotatedWith(ResourceDowngration.class);
        if (!CollectionUtils.isEmpty(methodSet)) {
            for (Method method : methodSet) {
                ResourceDowngration resourceDowngration = method.getAnnotation(ResourceDowngration.class);
                resourceDowngrationMap.put(resourceDowngration.resourceId(), resourceDowngration);
            }
        }
    }

    public ResourceDowngration getResouceDowngrationByResouceId(String resouceId) {
        ResourceDowngration downgration = resourceDowngrationMap.get(resouceId);
        Assert.notNull(downgration, " resourceId : " + resouceId + " can not found resourceDowngration");
        return downgration;
    }

    public void changeThreshold(String resourceId, Integer threshold) {
        ResourceDowngration downgration = this.getResouceDowngrationByResouceId(resourceId);
        InvocationHandler invocationHandler = Proxy.getInvocationHandler(downgration);
        try {
            Field field = invocationHandler.getClass().getDeclaredField("memberValues");
            field.setAccessible(true);
            Map<String, Object> memberMap = (Map<String, Object>) field.get(invocationHandler);
            if (memberMap.containsKey(MAX_THRESHOLD_KEY)) {
                memberMap.put(MAX_THRESHOLD_KEY, threshold);
                log.info("resouceId: {} change maxThreshold to {} ok !", resourceId, threshold);
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}

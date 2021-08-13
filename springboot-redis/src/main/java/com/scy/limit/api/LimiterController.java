package com.scy.limit.api;

import com.scy.limit.Limit;
import com.scy.limit.LimitType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 类名： LimiterController <br>
 * 描述：TODO <br>
 * 创建日期： 2020/4/13 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */
@RequestMapping("/redis")
public class LimiterController {

    private static final AtomicInteger ATOMIC_INTEGER_1 = new AtomicInteger();
    private static final AtomicInteger ATOMIC_INTEGER_2 = new AtomicInteger();
    private static final AtomicInteger ATOMIC_INTEGER_3 = new AtomicInteger();


    @Limit(key = "limitTest", period = 10, count = 3)
    @RequestMapping(value = "/limitTest", method = RequestMethod.GET)
    public int testLimiter() {
        return ATOMIC_INTEGER_1.incrementAndGet();
    }

    @Limit(key = "limitTest", period = 10, count = 3,limitType = LimitType.CUSTOMER)
    @RequestMapping(value = "/limitTest2", method = RequestMethod.GET)
    public int testLimiter2() {
        return ATOMIC_INTEGER_2.incrementAndGet();
    }

    @Limit(key = "limitTest", period = 10, count = 3,limitType = LimitType.IP)
    @RequestMapping(value = "/limitTest3", method = RequestMethod.GET)
    public int testLimiter3() {
        return ATOMIC_INTEGER_3.incrementAndGet();
    }
}

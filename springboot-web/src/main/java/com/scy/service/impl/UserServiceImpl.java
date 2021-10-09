package com.scy.service.impl;

import com.scy.service.UserService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * 类名： UserServiceImpl <br>
 * 描述：TODO <br>
 * 创建日期： 2021/9/18 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */
@Service
public class UserServiceImpl implements UserService {

    @Override
    public void testAsync() {
        System.out.println(Thread.currentThread().getName() + " Outerstart ....");
        asyncFun();
        System.out.println(Thread.currentThread().getName() + " Outerend ....");
    }

    @Async
    public void asyncFun() {
        System.out.println(Thread.currentThread().getName() + " Innerstart ....");
        try {
            Thread.sleep(1000000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " Innerend ....");
    }

    @Async
    @Override
    public void testAsync2() {
        System.out.println(Thread.currentThread().getName() + " 2start ....");
        try {
            Thread.sleep(100000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " 2end ....");
    }
}

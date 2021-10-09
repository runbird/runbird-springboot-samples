package com.scy.service;

import com.scy.domain.Order;

import java.math.BigDecimal;

/**
 * 类名： OrderService <br>
 * 描述： <br>
 * 创建日期： 2021/10/9 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */
public interface OrderService {

    long createOrder(Order order);

    Order find(long orderId);

    BigDecimal calculateTotalPrice(Order order);
}

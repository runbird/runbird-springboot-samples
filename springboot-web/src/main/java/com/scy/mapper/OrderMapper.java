package com.scy.mapper;

import com.scy.domain.Order;

import java.util.List;

/**
 * 类名： OrderMapper <br>
 * 描述： <br>
 * 创建日期： 2021/10/8 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */
public interface OrderMapper {
    // 根据订单Id查询
    Order find(long id);

    // 查询一个用户一段时间段内的订单列表
    List<Order> findByCustomerId(long customerId, long startTime, long endTime);

    // 保存一个订单
    long save(Order order);
}

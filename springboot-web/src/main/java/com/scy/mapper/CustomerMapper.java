package com.scy.mapper;

import com.scy.domain.Customer;

/**
 * 类名： CustomerMapper.xml <br>
 * 描述：TODO <br>
 * 创建日期： 2021/9/15 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */
public interface CustomerMapper {
    // 根据用户Id查询Customer(不查询Address)
    Customer find(long id);

    // 根据用户Id查询Customer(同时查询Address)
    Customer findWithAddress(long id);

    // 根据orderId查询Customer
    Customer findByOrderId(long orderId);

    // 持久化Customer对象
    int save(Customer customer);
}

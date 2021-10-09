package com.scy.service;

import com.scy.domain.Address;
import com.scy.domain.Customer;

import java.util.List;

/**
 * 类名： CustomerService <br>
 * 描述： <br>
 * 创建日期： 2021/10/9 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */
public interface CustomerService {
    long register(String name, String phone);

    long addAddress(long customerId, String street, String city, String country);

    List<Address> findAllAddress(long customerId);

    Customer find(long id);

    Customer findWithAddress(long id);
}

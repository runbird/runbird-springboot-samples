package com.scy.domain;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * 类名： Customer <br>
 * 描述：TODO <br>
 * 创建日期： 2021/9/15 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */
@Data
@Builder
public class Customer {
    private long id;
    private String name;
    private String phone;
    private List<Address> addresses;

    public Customer(long id, String name, String phone, List<Address> addresses) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.addresses = addresses;
    }
}

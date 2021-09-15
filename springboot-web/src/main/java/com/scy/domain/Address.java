package com.scy.domain;

import lombok.Builder;
import lombok.Data;

/**
 * 类名： Address <br>
 * 描述：TODO <br>
 * 创建日期： 2021/9/15 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */
@Data
@Builder
public class Address {
    private long id;
    private String street;
    private String city;
    private String country;
    private String customer;

    public Address(long id, String street, String city, String country, String customer) {
        this.id = id;
        this.street = street;
        this.city = city;
        this.country = country;
        this.customer = customer;
    }
}

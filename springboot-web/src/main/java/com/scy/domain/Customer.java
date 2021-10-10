package com.scy.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@NoArgsConstructor
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


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Customer{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", phone='").append(phone).append('\'');
        sb.append(", addresses=").append(addresses);
        sb.append('}');
        return sb.toString();
    }
}

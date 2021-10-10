package com.scy.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 类名： Order <br>
 * 描述：TODO <br>
 * 创建日期： 2021/9/15 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */
@Data
@Builder
@NoArgsConstructor
public class Order {
    private long id;
    private Customer customer;
    private Address deliveryAddress;
    private List<OrderItem> orderItems = new ArrayList<>();
    private long createTime;
    private BigDecimal totalPrice;

    public Order(long id, Customer customer, Address deliveryAddress, List<OrderItem> orderItems, long createTime, BigDecimal totalPrice) {
        this.id = id;
        this.customer = customer;
        this.deliveryAddress = deliveryAddress;
        this.orderItems = orderItems;
        this.createTime = createTime;
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Order{");
        sb.append("id=").append(id);
        sb.append(", customer=").append(customer);
        sb.append(", deliveryAddress=").append(deliveryAddress);
        sb.append(", orderItems=").append(orderItems);
        sb.append(", createTime=").append(createTime);
        sb.append(", totalPrice=").append(totalPrice);
        sb.append('}');
        return sb.toString();
    }
}

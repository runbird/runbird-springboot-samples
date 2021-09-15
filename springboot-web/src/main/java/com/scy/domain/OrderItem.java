package com.scy.domain;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 类名： OrderItem <br>
 * 描述：TODO <br>
 * 创建日期： 2021/9/15 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */
@Data
@Builder
public class OrderItem {
    private long id;
    private Product product;
    private int amout;
    private BigDecimal price;
    private long orderId;

    public OrderItem(long id, Product product, int amout, BigDecimal price, long orderId) {
        this.id = id;
        this.product = product;
        this.amout = amout;
        this.price = price;
        this.orderId = orderId;
    }
}

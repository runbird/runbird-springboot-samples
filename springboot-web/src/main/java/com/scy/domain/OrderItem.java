package com.scy.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@NoArgsConstructor
public class OrderItem {
    private long id;
    private Product product;
    private int amount;
    private BigDecimal price;
    private long orderId;

    public OrderItem(long id, Product product, int amount, BigDecimal price, long orderId) {
        this.id = id;
        this.product = product;
        this.amount = amount;
        this.price = price;
        this.orderId = orderId;
    }
}

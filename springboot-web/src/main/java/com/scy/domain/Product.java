package com.scy.domain;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 类名： Product <br>
 * 描述：TODO <br>
 * 创建日期： 2021/9/15 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */
@Data
@Builder
public class Product {
    private long id;
    private String name;
    private String description;
    private BigDecimal price;

    public Product(long id, String name, String description, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }
}

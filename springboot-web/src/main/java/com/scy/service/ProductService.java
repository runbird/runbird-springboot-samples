package com.scy.service;

import com.scy.domain.Product;

import java.util.List;

/**
 * 类名： ProductService <br>
 * 描述： <br>
 * 创建日期： 2021/10/9 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */
public interface ProductService {
    long createProduct(Product product);

    Product find(long productId);

    List<Product> find(String productName);

}

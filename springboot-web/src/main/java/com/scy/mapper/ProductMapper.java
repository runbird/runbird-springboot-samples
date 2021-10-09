package com.scy.mapper;

import com.scy.domain.Product;

import java.util.List;

/**
 * 类名： ProductMapper <br>
 * 描述： <br>
 * 创建日期： 2021/10/8 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */
public interface ProductMapper {
    // 根据id查询商品信息
    Product find(long id);

    // 根据名称搜索商品信息
    List<Product> findByName(String name);

    // 保存商品信息
    long save(Product product);
}

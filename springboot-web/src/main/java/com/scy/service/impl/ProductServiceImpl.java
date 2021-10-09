package com.scy.service.impl;

import com.scy.common.utils.DaoUtils;
import com.scy.domain.Product;
import com.scy.mapper.ProductMapper;
import com.scy.service.ProductService;
import org.assertj.core.util.Preconditions;
import org.assertj.core.util.Strings;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * 类名： ProductServiceImpl <br>
 * 描述： <br>
 * 创建日期： 2021/10/9 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Override
    public long createProduct(Product product) {
        // 检查product中的各个字段是否合法
        Preconditions.checkArgument(product != null, "product is null");
        Preconditions.checkArgument(!Strings.isNullOrEmpty(product.getName()), "product name is empty");
        Preconditions.checkArgument(!Strings.isNullOrEmpty(product.getDescription()), "description name is empty");
        Preconditions.checkArgument(product.getPrice().compareTo(new BigDecimal(0)) > 0, "price<=0 error");
        return DaoUtils.execute(sqlSession -> {
            ProductMapper mapper = sqlSession.getMapper(ProductMapper.class);
            return mapper.save(product);
        });
    }

    @Override
    public Product find(long productId) {
        // 检查productId参数是否合法
        Preconditions.checkArgument(productId > 0, "product id error");
        return DaoUtils.execute(sqlSession -> {
            ProductMapper mapper = sqlSession.getMapper(ProductMapper.class);
            return mapper.find(productId);
        });
    }

    @Override
    public List<Product> find(String productName) {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(productName), "productId error");
        return DaoUtils.execute(sqlSession -> {
            ProductMapper mapper = sqlSession.getMapper(ProductMapper.class);
            return mapper.findByName(productName);
        });
    }
}

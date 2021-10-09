package com.scy.mapper;

import com.scy.domain.OrderItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 类名： OrderItemMapper <br>
 * 描述： <br>
 * 创建日期： 2021/10/8 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */
public interface OrderItemMapper {
    OrderItem find(long id);

    List<OrderItem> findByOrderId(long orderId);

    long save(@Param("orderItem") OrderItem orderItem, @Param("orderId") long orderId);
}

package com.scy.service.impl;

import com.scy.common.utils.DaoUtils;
import com.scy.domain.Address;
import com.scy.domain.Order;
import com.scy.domain.OrderItem;
import com.scy.mapper.AddressMapper;
import com.scy.mapper.OrderItemMapper;
import com.scy.mapper.OrderMapper;
import com.scy.service.OrderService;
import org.assertj.core.util.Preconditions;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.List;

/**
 * 类名： OrderServiceImpl <br>
 * 描述： <br>
 * 创建日期： 2021/10/9 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Override
    public long createOrder(Order order) {
        Preconditions.checkArgument(order != null, "order is null");
        Preconditions.checkArgument(!CollectionUtils.isEmpty(order.getOrderItems()), "OrderItems is empty");
        return DaoUtils.execute(sqlSession -> {
            OrderMapper oderMapper = sqlSession.getMapper(OrderMapper.class);
            OrderItemMapper orderItemMapper = sqlSession.getMapper(OrderItemMapper.class);
            long cnt = oderMapper.save(order);
            if (cnt <= 0) {
                throw new RuntimeException("Save Order failed...");
            }
            long orderId = order.getId();
            for (OrderItem orderItem : order.getOrderItems()) {
                orderItemMapper.save(orderItem, orderId);
            }
            return orderId;
        });
    }

    @Override
    public Order find(long orderId) {
        Preconditions.checkArgument(orderId > 0, "order error");
        return DaoUtils.execute(sqlSession -> {
            OrderItemMapper orderItemMapper = sqlSession.getMapper(OrderItemMapper.class);
            List<OrderItem> orderItems = orderItemMapper.findByOrderId(orderId);
            //查询订单本身的信息
            OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
            Order order = orderMapper.find(orderId);
            order.setOrderItems(orderItems);
            //计算订单总额
            order.setTotalPrice(calculateTotalPrice(order));
            //查询订单关联的Address
            AddressMapper addressMapper = sqlSession.getMapper(AddressMapper.class);
            Address address = addressMapper.find(order.getDeliveryAddress().getId());
            order.setDeliveryAddress(address);
            return order;
        });
    }

    @Override
    public BigDecimal calculateTotalPrice(Order order) {
        List<OrderItem> orderItems = order.getOrderItems();
        BigDecimal totalPrice = new BigDecimal(0);
        for (OrderItem orderItem : orderItems) {
            BigDecimal itemPrices = orderItem.getProduct().getPrice()
                    .multiply(new BigDecimal(orderItem.getAmout()));
            orderItem.setPrice(itemPrices);
            totalPrice.add(itemPrices);
        }
        return totalPrice;
    }
}

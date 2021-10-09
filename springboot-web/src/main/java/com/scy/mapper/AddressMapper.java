package com.scy.mapper;

import com.scy.domain.Address;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 类名： AddressMapper.xml <br>
 * 描述：TODO <br>
 * 创建日期： 2021/9/15 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */
public interface AddressMapper {

    Address find(long id);

    List<Address> findAll(long customerId);

    Address findByOrderId(long orderId);

    // 存储Address对象，同时会记录关联的Customer
    int save(@Param("address") Address address, @Param("customerId") long customerId);
}

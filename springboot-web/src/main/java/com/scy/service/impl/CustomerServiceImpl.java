package com.scy.service.impl;

import com.scy.common.utils.DaoUtils;
import com.scy.domain.Address;
import com.scy.domain.Customer;
import com.scy.mapper.AddressMapper;
import com.scy.mapper.CustomerMapper;
import com.scy.service.CustomerService;
import org.assertj.core.util.Preconditions;
import org.assertj.core.util.Strings;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 类名： CustomerServiceImpl <br>
 * 描述： <br>
 * 创建日期： 2021/10/9 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */
@Service
public class CustomerServiceImpl implements CustomerService {
    @Override
    public long register(String name, String phone) {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(name), "name is empty");
        Preconditions.checkArgument(!Strings.isNullOrEmpty(phone), "phone is empty");
        return DaoUtils.execute(sqlSession -> {
            CustomerMapper mapper = sqlSession.getMapper(CustomerMapper.class);
            Customer customer = Customer.builder().name(name).phone(phone).build();
            int cnt = mapper.save(customer);
            if (cnt <= 0) {
                throw new RuntimeException("Save Customer failed ...");
            }
            return customer.getId();
        });
    }

    @Override
    public long addAddress(long customerId, String street, String city, String country) {
        Preconditions.checkArgument(customerId > 0, "customerId is empty");
        Preconditions.checkArgument(!Strings.isNullOrEmpty(street), "street is empty");
        Preconditions.checkArgument(!Strings.isNullOrEmpty(city), "city is empty");
        Preconditions.checkArgument(!Strings.isNullOrEmpty(country), "country is empty");
        return DaoUtils.execute(sqlSession -> {
            AddressMapper mapper = sqlSession.getMapper(AddressMapper.class);
            Address address = Address.builder().city(city).street(street).country(country).build();
            int cnt = mapper.save(address, customerId);
            if (cnt <= 0) {
                throw new RuntimeException("Save Address failed...");
            }
            return address.getId();
        });
    }

    @Override
    public List<Address> findAllAddress(long customerId) {
        // 检查用户id参数是否合法
        Preconditions.checkArgument(customerId > 0, "id error");
        return DaoUtils.execute(sqlSession -> {
            AddressMapper mapper = sqlSession.getMapper(AddressMapper.class);
            return mapper.findAll(customerId);
        });
    }

    @Override
    public Customer find(long id) {
        // 检查用户id参数是否合法
        Preconditions.checkArgument(id > 0, "id error");
        return DaoUtils.execute(sqlSession -> {
            CustomerMapper mapper = sqlSession.getMapper(CustomerMapper.class);
            return mapper.find(id);
        });
    }

    @Override
    public Customer findWithAddress(long id) {
        // 检查用户id参数是否合法
        Preconditions.checkArgument(id > 0, "id error");
        return DaoUtils.execute(sqlSession -> {
            CustomerMapper mapper = sqlSession.getMapper(CustomerMapper.class);
            return mapper.findWithAddress(id);
        });
    }
}

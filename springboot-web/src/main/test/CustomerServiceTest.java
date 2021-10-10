import com.google.common.collect.Lists;
import com.scy.Web;
import com.scy.domain.*;
import com.scy.service.CustomerService;
import com.scy.service.OrderService;
import com.scy.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

/**
 * 类名： CustomerServiceTest <br>
 * 描述： <br>
 * 创建日期： 2021/10/9 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */
@SpringBootTest(classes = Web.class)
@RunWith(SpringRunner.class)
public class CustomerServiceTest {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductService productService;

    @Test
    public void test01() {
        long customerId = customerService.register("张三", "123456789");
        long addressId = customerService.addAddress(customerId, "chang an", "chang an", "China");
        System.out.println(customerId);
        System.out.println(addressId);

        Customer customer = customerService.find(customerId);
        System.out.println(customer.toString());

        Customer cus = customerService.findWithAddress(addressId);
        System.out.println(cus.toString());

        List<Address> allAddress = customerService.findAllAddress(customerId);
        allAddress.forEach(x -> System.out.println(x.toString()));

        Product product = Product.builder().name("Mybatis").description("Mybatis source code").price(new BigDecimal(99)).build();
        long productId = productService.createProduct(product);
        System.out.println("create productId : " + productId);

        OrderItem orderItem = OrderItem.builder().amount(20).product(product).build();
        Order order = Order.builder().customer(customer).deliveryAddress(allAddress.get(0)).orderItems(Lists.newArrayList(orderItem)).build();

        long orderId = orderService.createOrder(order);
        System.out.println("create orderId : " + orderId);
        Order order2 = orderService.find(orderId);
        System.out.println(order2.toString());
    }
}

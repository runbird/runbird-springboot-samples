import com.scy.Web;
import com.scy.service.CustomerService;
import com.scy.service.OrderService;
import com.scy.service.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
    }
}

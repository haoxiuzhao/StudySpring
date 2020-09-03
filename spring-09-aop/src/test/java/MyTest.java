import com.zhao.service.UserService;
import com.zhao.service.UserServiceImpl;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        //动态代理代理的是接口
       UserService userService= context.getBean("userService", UserService.class);
       userService.delete();
    }
}

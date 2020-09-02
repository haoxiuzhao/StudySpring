import com.zhao.pojo.User;
import com.zhao.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        User user=context.getBean("user", User.class);
        UserService userservice=context.getBean("userService", UserService.class);
        System.out.println(user.toString());
        System.out.println(userservice.toString());

    }
}

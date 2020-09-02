import com.zhao.pojo.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
    public static void main(String[] args) {

       // User user=new User();
        //spring容器，就类似于婚介网站，在配置文件加载的时候，容器中管理的对象都已经初始化了，且只有一份，取出来都是同一个对象
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        User user = (User)context.getBean("hhffse");
        User user2 = (User)context.getBean("user");
        System.out.println(user==user2);//为true
        user.show();

    }

}

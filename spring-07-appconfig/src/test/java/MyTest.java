import com.zhao.config.MyConfig;
import com.zhao.pojo.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MyTest {
    public static void main(String[] args) {

        //如果完全使用了配置类方式去做，我们就只能通过AnnotationConfig 上下文来获取容器，通知配置类的class对象加载
        ApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
        User getUser=(User)context.getBean("getUser");
        System.out.println(getUser.getName());
    }
}

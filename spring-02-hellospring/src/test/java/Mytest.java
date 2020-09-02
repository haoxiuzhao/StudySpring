import com.zhao.pojo.Hello;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Mytest {
    public static void main(String[] args) {
        //获取spring的上下文对象
        ApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("beans.xml");
        //对象都在spring中管理，要使用直接去里面取出来就行
        Hello hello=(Hello) classPathXmlApplicationContext.getBean("hello");
        System.out.println(hello.toString());


    }
}

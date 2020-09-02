import com.zhao.pojo.Student;
import com.zhao.pojo.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class MyTest {
    public static void main(String[] args) {
        System.out.println("hahahaha1");
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        Student student=(Student) context.getBean("student");
        System.out.println(student.toString());

    }
    @Test
    public void test2(){
        ApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("user.xml");
        User user = (User) classPathXmlApplicationContext.getBean("user");
        User user2 = (User) classPathXmlApplicationContext.getBean("user2");
        User user3 = (User) classPathXmlApplicationContext.getBean("user2j");
        System.out.println(user);
        System.out.println(user2);
        System.out.println(user3);
        System.out.println(user2==user);
        System.out.println(user2==user3);

    }
}

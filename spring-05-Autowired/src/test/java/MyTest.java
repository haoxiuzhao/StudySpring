import com.zhao.pojo.Person;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class MyTest {
    @Test
    public void test()
    {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        Person person=context.getBean("person", Person.class);
        person.getCat().shout();
        person.getDog().shout();
    }

}

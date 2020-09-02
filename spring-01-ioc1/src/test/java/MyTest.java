import com.zhao.dao.UserDaoOracleImpl;
import com.zhao.dao.UserDaomysqlImpl;
import com.zhao.dao.UserDaoImpl;
import com.zhao.service.UserServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class MyTest {
    public static void main(String[] args) {
        //UserServiceImpl userServiceImpl=new UserServiceImpl();
        //通过接口往里面注入不同的对象
        //userServiceImpl.setUserDao(new UserDaoImpl());
        //userServiceImpl.getUser();
        //拿到spring容器
        ApplicationContext  classPathXmlApplicationContext =new ClassPathXmlApplicationContext("beans.xml");
        //容器在手，需要什么，就直接get什么，修改已经不需要修改代码，实现不同的操作，直接修改xml文件

        UserServiceImpl userServiceImpl =(UserServiceImpl)  classPathXmlApplicationContext.getBean("userserviceimpl");
        userServiceImpl.getUser();
    }
}

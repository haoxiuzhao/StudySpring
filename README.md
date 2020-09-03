













# Spring-Study

[Kuang shen Spring study in bilibili](https://www.bilibili.com/video/BV1WE411d7Dv?p=1)


## 1. 简介

spring理念：是现有的技术更加容易使用，本身是一个大杂烩。

- SSH：Struct2 + Spring + Hibernate
- SSM: SpringMVC + Spring + Mybatis

官网： https://spring.io/projects/spring-framework#overview 

官方下载： https://repo.spring.io/release/org/springframework/spring/ 

GitHub： https://github.com/spring-projects/spring-framework 



[Spring Web MVC](https://mvnrepository.com/artifact/org.springframework/spring-webmvc) **»** [5.2.5.RELEASE](https://mvnrepository.com/artifact/org.springframework/spring-webmvc/5.2.5.RELEASE)

```xml
<!-- https://mvnrepository.com/artifact/org.springframework/spring-webmvc -->
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-webmvc</artifactId>
    <version>5.2.5.RELEASE</version>
</dependency>

<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-jdbc</artifactId>
    <version>5.2.3.RELEASE</version>
</dependency>
```



- spring是开源的免费的容器。
- spring是一个轻量级的，非入侵式的。
- 控制反转（IOC），面向切面编程 (AOP)。
- 支持事务处理，对框架整合的支持。

总结：spring是一个轻量级的控制反转(IOC)和面向切面编程(AOP)的框架。



## 2.IOC理论

1. UserDao

2. UserDaoImp

3. UserSevice

4. UserServiceImp

在之前，用户的需求可能会影响原来的代码。

使用一个set。

```java
public void setUserDao(UserDao userDao){
    this.userDao = userDao;
}
```

- 之前是主动创建对象，控制权在程序员手上。

- 使用set之后，是被动接受对象。



## 3. Hello Spring

pojo中

```java
package com.hou.pojo;

public class Hello {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Hello{" +
                "name='" + name + '\'' +
                '}';
    }
}
```

resource种

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd">

    <!--bean = 对象-->
    <!--id = 变量名-->
    <!--class = new的对象-->
    <!--property 相当于给对象中的属性设值-->
    
    <bean id="hello" class="com.hou.pojo.Hello">
        <property name="name" value="Spring"/>
    </bean>
</beans>
```

test

```java
import com.hou.pojo.Hello;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Mytest {

    public static void main(String[] args) {
        //获取spring上下文对象
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        //我们的对象下能在都在spring·中管理了，我们要使用，直接取出来就可以了
        Hello hello = (Hello) context.getBean("hello");
        System.out.println(hello.toString());
    }
}
```

bean = 对象
id = 变量名
class = new的对象
property 相当于给对象中的属性设值

核心用set注入

第一个文件中

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="userdaomysql" class="com.hou.dao.UserDaoMysqlImpl"></bean>

    <bean id="userServiceImpl" class="com.hou.service.UserServiceImp">
        <!--ref引用spring中已经创建很好的对象-->
        <!--value是一个具体的值-->
        <property name="userDao" ref="userdaomysql"/>
    </bean>

</beans>
```



## 4. IOC创建对象的方式

1. 使用无参构造创建对象，默认。
2. 使用有参构造

下标赋值

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="user" class="com.hou.pojo.User">
        <constructor-arg index="0" value="hou"/>
    </bean>
</beans>
```

类型赋值（不建议使用）

```xml
<bean id="user" class="com.hou.pojo.User">
    <constructor-arg type="java.lang.String" value="dong"/>
</bean>
```

直接通过参数名

```xml
<bean id="user" class="com.hou.pojo.User">
    <constructor-arg name="name" value="hou"></constructor-arg>
</bean>
```

Spring类似于婚介网站！

你想不想要，对象都在里面。注册bean之后用不用被实例化。



## 5. Spring配置

**别名**

```xml
<bean id="user" class="com.hou.pojo.User">
    <constructor-arg name="name" value="hou"></constructor-arg>
</bean>

<alias name="user" alias="user2aaa"/>
```



**Bean的配置**

- id：bean的id标识符
- class：bean对象所对应的类型
- name：别名，更高级，可以同时取多个别名。



**import**

一般用于团队开发，它可以将多个配置文件，导入合并为一个

```xml
<import resource="beans.xml"/>
```



## 6. DI依赖注入

**构造器注入**

**set方式注入**（重点）

- 依赖：bean对象的创建依赖于容器
- 注入：bean对象中的所有属性，由容器来注入

【环境搭建】

1. 复杂类型
2. 真实测试对象

```java
package com.pojo;

import java.util.*;

public class Student {

    private String name;
    private Address address;

    private String[] books;
    private List<String> hobbies;

    private Map<String, String> card;
    private Set<String> game;

    private Properties infor;
    private String wife;

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", address=" + address +
                ", books=" + Arrays.toString(books) +
                ", hobbies=" + hobbies +
                ", card=" + card +
                ", game=" + game +
                ", infor=" + infor +
                ", wife='" + wife + '\'' +
                '}';
    }
}
```

```java
public class Address {
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Address{" +
                "address='" + address + '\'' +
                '}';
    }
}
```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="address" class="com.pojo.Address">
        <property name="address" value="xian"></property>
    </bean>

    <bean id="student" class="com.pojo.Student">
        <property name="name" value="hou"/>
        <property name="address" ref="address"/>

        <!--数组注入-->
        <property name="books">
            <array>
                <value>三国</value>
                <value>西游</value>
                <value>水浒</value>
            </array>
        </property>

        <!--list-->
        <property name="hobbies">
            <list>
                <value>eat</value>
                <value>drink</value>
                <value>play</value>
            </list>
        </property>

        <property name="card">
            <map>
                <entry key="1" value="12"/>
                <entry key="2" value="23"/>
            </map>
        </property>

        <property name="game">
            <set>
                <value>wangzhe</value>
                <value>daota</value>
                <value>lol</value>
            </set>
        </property>

        <property name="wife">
            <null></null>
        </property>

        <!--properties-->
        <property name="infor">
            <props>
                <prop key="id">20200405</prop>
                <prop key="name">hdk</prop>
            </props>
        </property>
    </bean>

</beans>
```

**第三方**

p标签和c标签

```java
package com.pojo;

public class User {

    private String name;
    private int age;

    public User() {
    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:p="http://www.springframework.org/schema/p"
       xmlns:c="http://www.springframework.org/schema/c"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd">

    <!--p命名空间注入/set注入-->
    <bean id="use" class="com.pojo.User" p:name="dong" p:age="10">
    </bean>

    <!--c命名空间/构造器-->
    <bean id="use2" class="com.pojo.User" c:name="kun" c:age="19"></bean>
</beans>
```



**bean的作用域**

![1586093707060](C:\Users\QHQ\AppData\Roaming\Typora\typora-user-images\1586093707060.png)

1. 单例模式（默认）

```xml
<bean id="use2" class="com.pojo.User" c:name="kun" c:age="19" scope="singleton"></bean>
```

2. 原型模式: 每次从容器中get的时候，都产生一个新对象！

```xml
<bean id="use2" class="com.pojo.User" c:name="kun" c:age="19" scope="prototype"></bean>
```

3. 其余的request、session、application这些只能在web开放中使用！



## 7. Bean的自动装配

- 自动装配是Spring是满足bean依赖的一种方式
- Spring会在上下文自动寻找，并自动给bean装配属性
- 

在Spring中有三种装配的方式

1. 在xml中显示配置
2. 在java中显示配置
3. 隐式的自动装配bean 【重要】



1. 环境搭建：一个人有两个宠物

2. byName自动装配：byName会自动查找，和自己对象set对应的值对应的id

   保证所有id唯一，**并且和set注入的值一致，id名字可以和set后面名字一样，或者set后面名字只是首字母大写都可以，如果id名字首字母大写，那会报错**

3. byType自动装配：byType会自动查找，和自己对象属性相同的bean

   保证所有的class唯一, 这里还是需要set方法，但是set后面方法名字没有要求，只要包含set方法就可以。

```java
package com.zhao.pojo;

public class Cat {
    public void shout(){
        System.out.println("miao miao");
    }
}

```

````java
package com.zhao.pojo;

public class Dog {
    public void shout(){
        System.out.println("wang wang");
    }
}

````

```java
package com.zhao.pojo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ImportResource;


public class Person {
    //如果显示定义了Autowired的required属性为false，说明这个对象可以为null，否则不允许为空
    @Autowired(required = false)
    private Cat cat;
    //@Autowired
   // @Qualifier(value = "dog12")
    //@Resource
    private Dog dog;
    private String  name;

    public Cat getCat() {
        return cat;
    }

//    public void setCat(Cat cat) {
//        this.cat = cat;
//    }

    public Dog getDog() {
        return dog;
    }

    //autowire="byname"是通过set后面的名字来进行匹配注入的，也就是bean id=dog22，如果setDog22就不会报错，如果set不是这个名字就会报错
    //跟person中定义的Dog dog对象名字无关
    public void setDog12(Dog dog) {
        this.dog = dog;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "cat=" + cat +
                ", dog=" + dog +
                ", name='" + name + '\'' +
                '}';
    }
}

```

```xml
<?xml version="1.0" encoding="UTF-8"?>
    <beans xmlns="http://www.springframework.org/schema/beans"
           xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
           xmlns:context="http://www.springframework.org/schema/context"
           xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        https://www.springframework.org/schema/context/spring-context.xsd">

        <context:annotation-config/>


    <bean class="com.zhao.pojo.Cat"/>
<!--    <bean  class="com.zhao.pojo.Dog"/>-->
    <bean id="dog12" class="com.zhao.pojo.Dog"/>




    <!-- autowire：byName会自动查找在容器上下文中查找，和自己对象set方法后面的值对应的beanid   -->
    <!-- autowire：byType会自动在容器上下文中查找，和自己对象属性类型相同的bean   -->
    <bean id="person" class="com.zhao.pojo.Person" autowire="byName">
<!--        <property name="cat" ref="cat"/>-->
<!--        <property name="dog" ref="dog"/>-->
<!--        <property name="name" value="zhaozhao"/>-->
    </bean>


</beans>
```



**使用注解自动装配**

jdk1.5支持的注解，spring2.5支持的注解

 The introduction of annotation-based configuration raised the question of whether this approach is “better” than XML. 

要使用注解须知

1、导入context约束 

2、配置注解的支持<context:annotation-config/>

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:context="http://www.springframework.org/schema/context"
    xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        https://www.springframework.org/schema/context/spring-context.xsd">

    <context:annotation-config/>

</beans>
```

@Autowired

在属性上个使用，也可以在set上使用

我们可以不用编写set方法了

```java
public class People {
    @Autowired
    private Cat cat;
    @Autowired
    private Dog dog;
    private String name;
}
```

```xml
@Nullable 字段标志的注解，说明这个字段可以为null
```

```java
public @interface Autowired {
    boolean required() default true;
}
```

Autowired默认是true，如上所示。

测试代码：

```java
public class Person {
    //如果显示定义了Autowired的required属性为false，说明这个对象可以为null，否则不允许为空
    @Autowired(required = false)
    private Cat cat;
    @Autowired
    @Qualifier(value = "dog12")
```





如果@Autowired自动装配环境比较复杂。自动装配无法通过一个注解完成的时候

我们可以使用@Qualifier(value = "dog")去配合@Autowired使用，指定一个唯一的id对象注入

```java
public class People {
    @Autowired
    private Cat cat;
    @Autowired
    @Qualifier(value = "dog")
    private Dog dog;
    private String name;
}
```

@Resource(name="dog")也可以

区别：

都是用来自动装配的，都可以放在属性字段上

- @Autowire通过byType实现，而且必须要求这个对象存在
- @Resource默认通过byName实现，如果找不到，通过byType实现，如果两个都找不到得情况下就会报错
- 还是都是需要xml文件的，文件中也必须要有 <bean  class="com.zhao.pojo.Dog"/>



## 8. 使用注解开发

在spring4之后，必须要保证aop的包导入

使用注解需要导入contex的约束

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:context="http://www.springframework.org/schema/context"
    xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        https://www.springframework.org/schema/context/spring-context.xsd">

    <context:annotation-config/>

</beans>
```

1. 属性如何注入

```java
@Component
public class User {
    
    @Value("dong")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
```

2. 衍生的注解

@Component有几个衍生注解，会按照web开发中，mvc架构中分层。

- dao （@Repository）
- service（@Service）
- controller（@Controller）

这四个注解功能一样的，都是代表将某个类注册到容器中，被spring托管，装配bean

3. 作用域

@Scope("singleton")

```java
@Component
@Scope("prototype")
public class User {

    @Value("dong")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
```

小结：

xml与注解

- xml更加万能，维护简单
- 注解，不是自己的类，使用不了，维护复杂

最佳实践：

- xml用来管理bean
- 注解只用来完成属性的注入
- 在使用过程中要注意，必须要让注解生效，就需要开启注解的支持

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        https://www.springframework.org/schema/context/spring-context.xsd">

    <context:annotation-config/>
    <!--指定要扫描的包-->
    <context:component-scan base-package="com.pojo"/>

</beans>
```



## 9. 使用java方式配置spring

现在完全不使用Spring的XML配置了，全权交给Java来做！

JavaConfig是Spring的一个子项目

JavaConfig

Spring的一个子项目，在spring4之后，，他成为了核心功能



实体类

```java
//这里注解的意思，就是说明这个类被spring接管了，注册到了容器中
@Component
public class User {

    @Value("come on")//属性注入值
   private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }
}

```

```java
配置文件
package com.zhao.config;

import com.zhao.pojo.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

//这个也会Spring容器托管，注册到容器中，因为他本来就是一个@Component
//@Configuration 代表就是一个配置类，就和我们之前看的beans.xml是一样的
  @Configuration
  @ComponentScan("com.zhao")
  @Import(MyConfig2.class)
  public class MyConfig {
      //注册一个bean，就相当于我们之前写的一个bean标签
      //这个方法的名字，就相当于bean标签中的id属性
      //这个方法的返回值，就相当于bean标签中的class属性
      @Bean
    public User getUser(){
        return  new User();//就是要返回要注入到bean的对象
    }

}

```

```java
测试类：
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

```

这种纯java配置方式 在springboot中，随处可见



## 10. 代理模式

为什么要学习代理模式？因为这就是SpringAOP的底层！【SpringAOP和SpringMVC】面试的时候必问

代理模式分类:

动态代理和静态代理

### 10.1静态代理

角色分析：

- 抽象角色：一般会使用接口或者抽象类解决

- 真实角色：被代理的角色

- 代理角色：代理真实的角色，代理真实角色后，我们一般会做一些附属操作

- 客户：访问代理的人

代码步骤：

1. 接口

   ```java
   public interface Rent {
       public  void rent();
   }
   
   ```

   

2. 真实角色

   ```java
   public class Host implements Rent {
   
       public void rent() {
           System.out.println("房东要出租房子");
   
       }
   
   
   
   }
   
   ```

   

3. 代理角色

   ```java
   package com.zhao.demo01;
   
   public class Proxy implements Rent{
       private Host host;
   
       public Proxy() {
   
       }
   
       public Proxy(Host host) {
           this.host = host;
       }
       public void rent(){
           host.rent();
       }
       //看房
       public void seeHouse(){
           System.out.println("中介带看房");
       }
       //收中介费
       public void  fare(){
           System.out.println("收中介费");
       }
   }
   
   ```

   

4. 客户端访问代理角色

   ```java
   package com.zhao.demo01;
   
   public class Client {
       public static void main(String[] args) {
          //房东要出租房子
           Host host=new Host();
           //代理，中介帮房东租房子，但是呢，代理一般会有一些附属操作
           Proxy proxy=new Proxy(host);
           //你不用面对房东，直接找中介就行
           proxy.rent();
   
       }
   }
   
   ```

   

代理模式的好处：

- 可以使真实角色的操作更加纯粹，不用关注一些公共的业务

- 公共也就交给代理角色，实现了业务的分工

- 公共业务发生扩展的时候，方便集中管理

缺点：

- 一个真实角色就会产生一个代理角色，代码量会翻倍，开发效率会变低



动态代理类是动态生成的，不是我们直接写好的！

动态代理：基于接口，基于类

- 基于接口：JDK的动态代理【使用】
- 基于类：cglib
- java字节码

InvocationHandler

Proxy

```java
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

//会这个类，自动生成代理类
public class ProxyInvocation implements InvocationHandler {

    //被代理的接口
    private Rent rent;

    public void setRent(Rent rent) {
        this.rent = rent;
    }

    //生成代理类
    public Object getProxy(){
        return Proxy.newProxyInstance(this.getClass().getClassLoader(),rent.getClass().getInterfaces(),this);
    }

    //处理代理实例，并返回结果
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        seeHouse();
        Object result = method.invoke(rent, args);
        fare();
        return result;
    }

    public void seeHouse(){
        System.out.println("see house");
    }

    public void fare(){
        System.out.println("fare");
    }
}
```

```java
public interface Rent {
    void rent();
}
```

```java
public class Host implements Rent {
    public void rent() {
        System.out.println("host rent");
    }
}
```

```java
public class Client {

    public static void main(String[] args) {
        //真实角色
        Host host = new Host();

        //代理角色
        ProxyInvocation proxyInvocation = new ProxyInvocation();

        //通过调用程序处理角色来处理我们要调用的接口对象
        proxyInvocation.setRent(host);

        Rent proxy = (Rent) proxyInvocation.getProxy();  //这里的proxy是动态生成的

        proxy.rent();
    }
}
```

### 10.2 动态代理

- 动态代理和静态代理角色一样

- 动态代理的类是动态生成的，不是我们直接写好的

- 动态代理分为两大类，基于接口的动态代理，基于累的动态代理

  - 基于接口---JDK动态代理

  - 基于类：cglib
  - java字节码实现：javasist

  需要了解两个类：Proxy：代理，InvocationHandler：调用处理程序

  

  动态代理的好处：

  - 可以使真实角色的操作更加纯粹，不用关注一些公共的业务

  - 公共也就交给代理角色，实现了业务的分工
  - 公共业务发生扩展的时候，方便集中管理
  - 一个动态代理类代理的是一个接口，一般就是对应的一类业务
  - 一个动态代理类可以代理多个类，只要是实现了同一个接口即可

  

## 11.AOP（超级重点）

```xml
<dependencies>
    <dependency>
        <groupId>org.aspectj</groupId>
        <artifactId>aspectjweaver</artifactId>
        <version>1.9.4</version>
    </dependency>
</dependencies>
```

方法一：使用spring接口【springAPI接口实现】

```xml
<?xml version="1.0" encoding="UTF-8"?>
    <beans xmlns="http://www.springframework.org/schema/beans"
           xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
           xmlns:aop="http://www.springframework.org/schema/aop"
           xmlns:context="http://www.springframework.org/schema/context"
           xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/aop
         https://www.springframework.org/schema/aop/spring-aop.xsd
        http://www.springframework.org/schema/context
        https://www.springframework.org/schema/context/spring-context.xsd">

        <context:annotation-config/>

<!--    注册·beans-->
    <bean id="userService" class="com.zhao.service.UserServiceImpl"/>
    <bean id="log"  class="com.zhao.log.Log"/>
    <bean id="afterLog" class="com.zhao.log.AfterLog"/>
<!--  方式一使用原生spring API接口  -->
<!--    配置aop,导入aop约束-->
<aop:config>
     <!--    切入点：expression：表达式，execution（要执行的位置）-->
    <aop:pointcut id="pointcut" expression="execution(* com.zhao.service.UserServiceImpl.*(..) )"/>
    <!--   执行环绕增强 -->
     <!--    下面这句话的意思就是将Log类切入到pointcut这个execution方法上面-->
    <aop:advisor advice-ref="log" pointcut-ref="pointcut"/>
    <aop:advisor advice-ref="afterLog" pointcut-ref="pointcut"/>
</aop:config>






</beans>
```

- 其中，在设置切入点的方法时，execution(* com.zhao.service.UserServiceImpl.* *(..) )，*代表了在这个这个包下的所有方法，如果是制定了一个方法，比如说是execution(* com.zhao.service.UserServiceImpl.add(..) )，也即是说在调用add方法时，才会可以进行切入进去。如果不是add方法，程序可以执行方法，但是切入是不会执行的。

```xml
<aop:pointcut id="pointcut" expression="execution(* com.zhao.service.UserServiceImpl.*(..) )"/>
```

测试类：

```java
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

```



service接口：

```java
package com.zhao.service;

public interface UserService {
    public void add();
    public void update();
    public void delete();
    public void selete();

}

```

service接口实现类;

```java
package com.zhao.service;

public class UserServiceImpl  implements UserService{
    @Override
    public void add() {
        System.out.println("增加了一个用户");
    }

    @Override
    public void update() {
        System.out.println("更新了一个用户");

    }

    @Override
    public void delete() {
        System.out.println("删除了一个用户");

    }

    @Override
    public void selete() {
        System.out.println("查询了一个用户");

    }
}

```

在切面插入的两个方法或者两个功能：

befor log：

```java
package com.zhao.log;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

public class Log implements MethodBeforeAdvice {

    //method，要执行的目标对象的方法，args参数，target目标对象
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println(target.getClass().getName()+"的"+method.getName()+"被执行了");
    }
}

```

after log；

```java
package com.zhao.log;

import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

public class AfterLog implements AfterReturningAdvice {
    //returnvalue是返回值
    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        System.out.println("执行了"+method.getName()+"方法，返回结果为"+returnValue);

    }
}

```

输出结果：

![image-20200903223752660](C:\Users\haoxi\AppData\Roaming\Typora\typora-user-images\image-20200903223752660.png)





方法二：自定义来实现AOP【主要是切面定义】

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/aop
        https://www.springframework.org/schema/aop/spring-aop.xsd">

    <!--注册bean-->
    <bean id="userservice" class="com.service.UserServiceImp"></bean>
    <bean id="log" class="com.log.Log"/>
    <bean id="afterlog" class="com.log.AfterLog"/>

    <bean id="diy" class="com.diy.DiyPointcut">
    </bean>
    <aop:config>
        <!--自定义切面-->
        <aop:aspect ref="diy">
            <!--切入点-->
            <aop:pointcut id="point" expression="execution(* com.service.UserServiceImp.*(..))"/>
            <aop:before method="before" pointcut-ref="point"/>
            <aop:after method="after" pointcut-ref="point"/>
        </aop:aspect>
    </aop:config>

</beans>
```

```java
public class DiyPointcut {

    public void before(){
        System.out.println("before");
    }

    public void after(){
        System.out.println("after");
    }
}
```

方法三：注解方式

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/aop
        https://www.springframework.org/schema/aop/spring-aop.xsd">
    
    <bean id="ann" class="com.diy.Annotation"></bean>
    <aop:aspectj-autoproxy/>
    <!--注册bean-->
    <bean id="userservice" class="com.service.UserServiceImp"></bean>
    
</beans>
```

```java
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect  //标注这个类是一个切面
public class Annotation {

    @Before("execution(* com.service.UserServiceImp.*(..))")
    public void before(){
        System.out.println("before");
    }

    @After("execution(* com.service.UserServiceImp.*(..))")
    public void after(){
        System.out.println("after");
    }

    //在环绕增强中，我们可以给地暖管一个参数，代表我们要获取切入的点
    @Around("execution(* com.service.UserServiceImp.*(..))")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("around");

        Object proceed = joinPoint.proceed();

        System.out.println("after around");
    }
}
```



## 12. 整合mybatis

文档： https://mybatis.org/spring/zh/ 

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>spring-study</artifactId>
        <groupId>com.hou</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>spring-10-mybatis</artifactId>

    <dependencies>
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>5.1.47</version>
        </dependency>

        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis-spring</artifactId>
            <version>2.0.4</version>
        </dependency>

        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-jdbc</artifactId>
            <version>5.2.3.RELEASE</version>
        </dependency>


        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis</artifactId>
            <version>3.5.2</version>
        </dependency>

        <dependency>
            <groupId>org.aspectj</groupId>
            <artifactId>aspectjweaver</artifactId>
            <version>1.9.4</version>
        </dependency>

        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.18.12</version>
        </dependency>
    </dependencies>

    <build>
        <resources>
            <resource>
                <directory>src/main/resources</directory>
                <includes>
                    <include>**/*.properties</include>
                    <include>**/*.xml</include>
                </includes>
            </resource>
            <resource>
                <directory>src/main/java</directory>
                <includes>
                    <include>**/*.properties</include>
                    <include>**/*.xml</include>
                </includes>
                <filtering>true</filtering>
            </resource>
        </resources>
    </build>

</project>
```

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">

<configuration>

    <typeAliases>
        <package name="com.pojo"/>
    </typeAliases>

    <environments default="development">
        <environment id="development">
            <transactionManager type="JDBC"/>
            <dataSource type="POOLED">
                <property name="driver" value="com.mysql.jdbc.Driver"/>
                <property name="url" value="jdbc:mysql://111.230.212.103:3306/mybatis?userSSL=true&amp;
                userUnicode=true&amp;characterEncoding=UTF-8"/>
                <property name="username" value="root"/>
                <property name="password" value="hdk123"/>
            </dataSource>
        </environment>
    </environments>

    <mappers>
        <mapper class="com.mapper.UserMapper"/>
    </mappers>
</configuration>
```

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="com.mapper.UserMapper">

    <select id="selectUser" resultType="user">
        select * from mybatis.user;
    </select>

</mapper>
```

```java
public interface UserMapper {
    List<User> selectUser();
}
```

整合

方法一：

![1586177510119](C:\Users\QHQ\AppData\Roaming\Typora\typora-user-images\1586177510119.png)

UserMapperImpl

```java
package com.mapper;

import com.pojo.User;
import org.mybatis.spring.SqlSessionTemplate;

import java.util.List;

public class UserMapperImpl implements UserMapper {

    private SqlSessionTemplate sqlSessionTemplate;

    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

    public List<User> selectUser() {
        UserMapper mapper = sqlSessionTemplate.getMapper(UserMapper.class);
        return mapper.selectUser();
    }
}
```

mybatis.xml

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">

<configuration>

    <typeAliases>
        <package name="com.pojo"/>
    </typeAliases>

</configuration>
```

spring.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/aop
        https://www.springframework.org/schema/aop/spring-aop.xsd">

    <!--data source-->
    <bean id="datasource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
        <property name="driverClassName" value="com.mysql.jdbc.Driver"/>
        <property name="url" value="jdbc:mysql://111.230.212.103:3306/mybatis?userSSL=true&amp;
                userUnicode=true&amp;characterEncoding=UTF-8"/>
        <property name="username" value="root"/>
        <property name="password" value="hdk123"/>
    </bean>

    <!--sqlsession-->
    <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
        <property name="dataSource" ref="datasource" />
        <!--bound mybatis-->
        <property name="configLocation" value="classpath:mybatis-config.xml"/>
        <property name="mapperLocations" value="classpath:com/mapper/UserMapper.xml"/>
    </bean>

    <bean id="sqlSession" class="org.mybatis.spring.SqlSessionTemplate">
        <constructor-arg index="0" ref="sqlSessionFactory"/>
    </bean>

    <bean id="userMapper" class="com.mapper.UserMapperImpl">
        <property name="sqlSessionTemplate" ref="sqlSession"></property>
    </bean>

</beans>
```

test

```java
import com.mapper.UserMapper;
import com.pojo.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;


public class Mytest {


    public static void main(String[] args) throws IOException {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-dao.xml");
        UserMapper userMapper = context.getBean("userMapper", UserMapper.class);

        for (User user : userMapper.selectUser()) {
            System.out.println(user);
        }
    }
}

```



方法二：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd">

    <!--data source-->
    <bean id="datasource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
        <property name="driverClassName" value="com.mysql.jdbc.Driver"/>
        <property name="url" value="jdbc:mysql://111.230.212.103:3306/mybatis?userSSL=true&amp;
                userUnicode=true&amp;characterEncoding=UTF-8"/>
        <property name="username" value="root"/>
        <property name="password" value="hdk123"/>
    </bean>

    <!--sqlsession-->
    <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
        <property name="dataSource" ref="datasource" />
        <!--bound mybatis-->
        <property name="configLocation" value="classpath:mybatis-config.xml"/>
        <property name="mapperLocations" value="classpath:com/mapper/UserMapper.xml"/>
    </bean>

    <!--<bean id="sqlSession" class="org.mybatis.spring.SqlSessionTemplate">-->
        <!--<constructor-arg index="0" ref="sqlSessionFactory"/>-->
    <!--</bean>-->

    <!--<bean id="userMapper" class="com.mapper.UserMapperImpl">-->
        <!--<property name="sqlSessionTemplate" ref="sqlSession"></property>-->
    <!--</bean>-->

    <bean id="userMapper2" class="com.mapper.UserMapperIml2">
        <property name="sqlSessionFactory" ref="sqlSessionFactory"></property>
    </bean>

</beans>
```

```java
package com.mapper;

import com.pojo.User;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.util.List;

public class UserMapperIml2 extends SqlSessionDaoSupport implements UserMapper {
    public List<User> selectUser() {
        SqlSession sqlSession = getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        return mapper.selectUser();
    }
}
```



## 13. 声明式事务

- 要么都成功，要么都失败
- 十分重要，涉及到数据一致性
- 确保完整性和一致性

事务的acid原则：

- 原子性

- 一致性

- 隔离性

  - 多个业务可能操作一个资源，防止数据损坏

- 持久性

  - 事务一旦提交，无论系统发生什么问题，结果都不会被影响。

  

Spring中的事务管理

- 声明式事务
- 编程式事务

声明式事务

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:tx="http://www.springframework.org/schema/tx"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/tx
        https://www.springframework.org/schema/tx/spring-tx.xsd
        http://www.springframework.org/schema/aop
        https://www.springframework.org/schema/aop/spring-tx.aop">

    <!--data source-->
    <bean id="datasource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
        <property name="driverClassName" value="com.mysql.jdbc.Driver"/>
        <property name="url" value="jdbc:mysql://111.230.212.103:3306/mybatis?userSSL=true&amp;
                userUnicode=true&amp;characterEncoding=UTF-8"/>
        <property name="username" value="root"/>
        <property name="password" value="hdk123"/>
    </bean>

    <!--sqlsession-->
    <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
        <property name="dataSource" ref="datasource" />
        <!--bound mybatis-->
        <property name="configLocation" value="classpath:mybatis-config.xml"/>
        <property name="mapperLocations" value="classpath:com/mapper/*.xml"/>
    </bean>

    <!--声明式事务-->
    <bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
        <constructor-arg ref="datasource" />
    </bean>

    <!--结合aop实现事务置入-->
    <!--配置事务的类-->
    <tx:advice id="tx1" transaction-manager="transactionManager">
        <!--给哪些方法配置事务-->
        <!--配置事务的传播特性-->
        <tx:attributes>
            <tx:method name="add" propagation="REQUIRED"/>
            <tx:method name="delete" propagation="REQUIRED"/>
            <tx:method name="update" propagation="REQUIRED"/>
            <tx:method name="*" propagation="REQUIRED"/>
            <tx:method name="query" read-only="true"/>
        </tx:attributes>
    </tx:advice>

    <!--配置事务切入-->
    <aop:config>
        <aop:pointcut id="txpointxut" expression="execution(* com.mapper.*.*(..))"/>
        <aop:advisor advice-ref="tx1" pointcut-ref="txpointxut"/>
    </aop:config>

</beans>
```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd">

    <import resource="spring-dao.xml"/>

    <bean id="userMapper2" class="com.mapper.UserMapperIml2">
        <property name="sqlSessionFactory" ref="sqlSessionFactory"></property>
    </bean>

</beans>
```

Mapper

```java
package com.mapper;

import com.pojo.User;

import java.util.List;

public interface UserMapper {
    List<User> selectUser();
    int addUser(User user);
    int delete(int id);
}
```

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="com.mapper.UserMapper">
    <select id="selectUser" resultType="user">
        select * from mybatis.user;
    </select>

    <insert id="addUser" parameterType="user">
        insert into mybatis.user (id, name, pwd) values
        (#{id}, #{name}, #{pwd})
    </insert>

    <delete id="delete" parameterType="int">
        delete from mybatis.user where id=#{id}
    </delete>
</mapper>
```

```java
package com.mapper;

import com.pojo.User;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.util.List;

public class UserMapperIml2 extends SqlSessionDaoSupport implements UserMapper {

    public List<User> selectUser() {
        User user = new User(6, "long", "zhi");
        SqlSession sqlSession = getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        mapper.addUser(user);
        mapper.delete(6);
        return mapper.selectUser();
    }

    public int addUser(User user) {
        return getSqlSession().getMapper(UserMapper.class).addUser(user);
    }

    public int delete(int id) {
        return getSqlSession().getMapper(UserMapper.class).delete(id);
    }
}
```

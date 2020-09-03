package com.zhao.demo04;
import com.zhao.demo02.*;
public class Client {
    public static void main(String[] args) {
        //真实角色无论如何都会有，无法省略
        UserServiceImpl userService=new UserServiceImpl();
        //代理角色不存在
      //  Host host=new Host();
        //代理角色
        ProxyInvocationHandler pih = new ProxyInvocationHandler();
        //通过调用程序处理角色来处理我们要调用的接口对象！
        pih.setTarget(userService);//设置要代理的对象
        //动态生成代理类
        UserService proxy = (UserService)pih.getProxy();//这里proxy就是动态生成的我们并没有写
        proxy.add();

    }

}

package com.zhao.demo02;

public class Client {
    public static void main(String[] args) {

        UserServiceImpl userservice=new UserServiceImpl();
        //没有通过中介的时候
        //userservice.add();

        //接下来通过中介，增加额为的服务，不需要更外原来的代码逻辑结构
        UserServiceProxy userServiceProxy=new UserServiceProxy();
        userServiceProxy.setUserService(userservice);
        userServiceProxy.delete();

    }

}

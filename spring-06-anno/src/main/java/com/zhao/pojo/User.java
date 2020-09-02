package com.zhao.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

//等价于<bean id="user" class="com.zhao.pojo.User"/>
//@Component组件
@Component
@Scope("singleton")
public class User {
    @Value("118")
    //相当于<property name="name" value="kuangshe"/>
    public String name="zhao";

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }
}

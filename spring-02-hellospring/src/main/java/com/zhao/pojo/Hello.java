package com.zhao.pojo;

public class Hello {

    String name;

    public String getName() {
        return name;
    }


    //依赖注入需要依靠set方法
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

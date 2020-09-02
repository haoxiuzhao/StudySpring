package com.zhao.pojo;

public class User2 {
    public User2(){
        System.out.println("User2 无参构造成功");
    }
    public User2(String name){
        this.name=name;
        System.out.println("User2 有参构造成功");
    }
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User2{" +
                "name='" + name + '\'' +
                '}';
    }
}

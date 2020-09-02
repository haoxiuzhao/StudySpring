package com.zhao.pojo;

public class User {
    private String name;
    private  int age;
    public User(){
        System.out.println("User 无参构造");
    }
    public User(String name,int age){
        this.name=name;
        this.age=age;
        System.out.println("Use r有参构造");
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

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

package com.zhao.service;

import com.zhao.dao.UserDao;

public class UserServiceImpl implements UserService{
    private UserDao userDao;
    int age;

    //利用Set动态实现值的注入
    public void setUserDao(UserDao userDao)
    {
        this.userDao=userDao;
    }




    public void getUser() {
        userDao.getUser();

    }


}

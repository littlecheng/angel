package com.weitao.aop;

import com.weitao.aop.IUserDao;

public class UserDao implements IUserDao {
    @Override
    public void save() {
        //int i =1/0; 用于测试异常通知
        System.out.println("保存用户...");
    }
}

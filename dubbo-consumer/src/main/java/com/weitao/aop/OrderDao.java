package com.weitao.aop;

public class OrderDao {
    public void save() {
        //int i =1/0; 用于测试异常通知
        System.out.println("保存订单...");
    }
}

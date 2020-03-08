package com.weitao.inter;

public class Student implements Person<Integer> {
    @Override
    public Integer parent() {
        return null;
    }

    @Override
    public String eat() {
        return null;
    }

    public static <T> T println(T t){
        System.out.println(t);
        return  t;
    }
}

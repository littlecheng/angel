package com.weitao.entity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class PropertiesTest {

    @Autowired
    Dog dog =new Dog();


    @Test
    public void contextLoads() {
        System.out.println(dog);
        System.out.println(dog.getMap().get("sss"));
        System.out.println(dog.getList().get(0));
        System.out.println(dog.getList().get(1));
    }
}


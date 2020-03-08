package com.weitao.aop.demo;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
public class MainConfigOfAop {

    @Bean
    public  MathCalculator mathCalculator(){
        System.out.println("mathCalculator bean");
        return new MathCalculator();
    }

    @Bean
    public LogAspects logAspects(){
        System.out.println("logAspects bean");
        return new LogAspects();
    }
}

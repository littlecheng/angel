package com.weitao;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.apache.dubbo.container.Main;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @title: HelloDubboServiceUserProviderApplication
 * @projectName angel
 * @description: TODO
 * @author Administrator
 * @date 2020/5/715:33
 */
@SpringBootApplication @EnableDubbo @MapperScan(basePackages = "com.weitao.mapper")//配置mapper扫描的路径（Mybatis的mapper接口）
public class HelloDubboServiceUserProviderApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(HelloDubboServiceUserProviderApplication.class, args);
        Main.main(args);
    }
}

package com.weitao;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.apache.dubbo.container.Main;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Administrator
 * @title: HelloDubboServiceUserProviderApplication
 * @projectName angel
 * @description: 先启动提供者，再启动消费者
 * @date 2020/5/715:33
 */
@SpringBootApplication(exclude = DruidDataSourceAutoConfigure.class)
@EnableDubbo
@MapperScan(basePackages = "com.weitao.mapper")//配置mapper扫描的路径（Mybatis的mapper接口）
public class HelloDubboServiceUserProviderApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(HelloDubboServiceUserProviderApplication.class, args);
        Main.main(args);
    }
}

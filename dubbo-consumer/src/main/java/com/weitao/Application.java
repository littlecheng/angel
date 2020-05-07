package com.weitao;

import org.apache.dubbo.common.logger.Logger;
import org.apache.dubbo.common.logger.LoggerFactory;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @title: Application
 * @projectName angel
 * @description: TODO
 * @author Administrator
 * @date 2020/5/215:20
 */
@SpringBootApplication @EnableDubbo public class Application
{

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public static void main(String[] args)
    {
        SpringApplication.run(Application.class);
    }

}
package com.weitao;

import org.apache.dubbo.common.logger.Logger;
import org.apache.dubbo.common.logger.LoggerFactory;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Administrator
 * @title: Application
 * @projectName angel
 * @description: TODO
 * @date 2020/5/215:20
 */
@SpringBootApplication
@EnableDubbo
public class Application {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {

        logger.info("消费者启动");
        SpringApplication.run(Application.class);
    }

}
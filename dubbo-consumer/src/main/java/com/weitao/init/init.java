package com.weitao.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class init implements CommandLineRunner {
    private final static Logger logger = LoggerFactory.getLogger(init.class);

    @Override
    public void run(String... args) throws Exception {



        logger.debug("logback 成功了");
        logger.info("logback 成功了  TRACE < DEBUG <  INFO < WARN <  ERROR");
        logger.warn("logback 成功了");
        logger.error("logback 成功了");
        logger.debug("init before SpringApplication has started");
    }
}

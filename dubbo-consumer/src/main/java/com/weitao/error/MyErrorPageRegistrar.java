package com.weitao.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

@Configuration
public class MyErrorPageRegistrar implements ErrorPageRegistrar {
    private static final Logger logger = LoggerFactory.getLogger(MyErrorPageRegistrar.class);

    @Override
    public void registerErrorPages(ErrorPageRegistry registry) {
        logger.info("错误");
        registry.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/error/404"));
        registry.addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error/500"));
    }

}


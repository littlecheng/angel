package com.weitao.controller;

import com.weitao.service.UserService;
import org.apache.dubbo.config.annotation.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController @RequestMapping(value = "/index")
public class IndexController {

    private final static Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Reference
    private UserService userService;

    @RequestMapping("/home/{name}")
    String home(@PathVariable("name") String name ) {
        logger.info("name="+name);
        return "Hello World! "+ name;
    }

    @RequestMapping("/getUser/{id}")
    public String GetUser(@PathVariable int id){
        return userService.Sel(id).toString();
    }

}

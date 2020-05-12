package com.weitao.controller;

import com.weitao.entity.User;
import com.weitao.service.UserService;
import org.apache.dubbo.config.annotation.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController @RequestMapping(value = "/index")
public class IndexController {

    private final static Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Reference
    private UserService userService;


    @RequestMapping("/getUser/{id}") public User GetUser(@PathVariable int id)
    {
        return userService.Sel(id);
    }

    @RequestMapping("/save/{userName}/{passWord}/{realName}") public String SaveUser(@PathVariable String userName,
        @PathVariable String passWord, @PathVariable String realName)
    {
        User user = new User();
        user.setUserName(userName);
        user.setPassWord(passWord);
        user.setRealName(realName);
        userService.Save(user);
        return "user saved";
    }

    @RequestMapping("/findAll") public List<User> findAll()
    {
        return userService.SelectAll();
    }


}

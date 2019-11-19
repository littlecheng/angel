package com.weitao.controller;


import com.weitao.entity.Dog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Calendar;

@RestController
@RequestMapping("/pp")
public class PropertiesController {

    @Autowired
    Dog dog;

    @RequestMapping("")
    @ResponseBody
    Dog home() {
        System.out.println(new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()));
        return dog;
    }
}

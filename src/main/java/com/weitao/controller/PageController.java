package com.weitao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;;import java.util.HashMap;

@Controller
public class PageController {
    @RequestMapping("/index.html")
    public String index() {
        return "index";
    }


    @RequestMapping("/hello")
    public String helloHtml(HashMap<String,Object> map){
        map.put("hello","HelloWord");
        return"/index";
    }
}
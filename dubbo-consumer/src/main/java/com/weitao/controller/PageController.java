package com.weitao.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller @RequestMapping(value = "/page")
public class PageController {
    private final static Logger logger = LoggerFactory.getLogger(PageController.class);

    @RequestMapping("/hello") public String helloHtml(
        @RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model)
    {
        model.addAttribute("name", name);
        return "hello.html";
    }

    @RequestMapping("/home/{name}") String home(@PathVariable("name") String name)
    {
        logger.info("name=" + name);
        return "Hello World! " + name;
    }
}
package com.weitao.controller;

import com.weitao.vo.Greeting;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @title: JqueryController
 * @projectName angel
 * @description: TODO
 * @author Administrator
 * @date 2020/5/1022:06
 */
@RestController @RequestMapping(value = "jquery") public class JqueryController
{
    private static final String template = "Hello,%s!";

    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/greeting") public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name)
    {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

    @RequestMapping("/home") public String index()
    {
        return "home";
    }

}

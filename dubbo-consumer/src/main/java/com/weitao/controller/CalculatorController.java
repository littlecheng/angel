package com.weitao.controller;

import com.weitao.service.CalculatorService;
import org.apache.dubbo.config.annotation.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/calculator")
public class CalculatorController {

    private static final Logger log = LoggerFactory.getLogger(CalculatorController.class);

    @Reference
    private CalculatorService calculatorService;

    @RequestMapping("/calculator/{id}/{is}")
    public int GetResult(@PathVariable int id, @PathVariable int is) {
        log.info("enter .... ");
        log.debug("debug  起作用吗");//配置info级别 所以debug级别不记录
        return calculatorService.div(id, is);
    }
}

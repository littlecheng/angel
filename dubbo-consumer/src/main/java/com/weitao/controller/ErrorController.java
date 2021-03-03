package com.weitao.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/error")
public class ErrorController {
    private final static Logger logger = LoggerFactory.getLogger(ErrorController.class);

    /**
     * 404页面
     */
    @GetMapping(value = "/404")
    public ModelAndView error_404(HttpServletRequest request, Model model) {
        logger.info("404页面");
        return new ModelAndView("/error/404.html");
    }

    /**
     * 500页面
     */
    @GetMapping(value = "/500")
    public ModelAndView error_500() {
        return new ModelAndView("/error/500.html");
    }

}

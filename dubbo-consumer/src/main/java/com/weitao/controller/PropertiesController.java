package com.weitao.controller;

import com.weitao.vo.Dog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @title: PropertiesController
 * @projectName angel
 * @description: TODO
 * @author Administrator
 * @date 2020/5/720:46
 */
@RestController public class PropertiesController
{
    @Autowired private Dog dog;

    @RequestMapping("/dog") @ResponseBody public Dog thing()
    {
        //  System.out.println(new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()));
        return dog;
    }
}

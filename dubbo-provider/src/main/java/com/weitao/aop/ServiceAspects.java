package com.weitao.aop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;

/**
 * 服务层切面
 */
@Aspect
@Component
public class ServiceAspects {
    @Pointcut(value = "@annotation(com.weitao.annotation.ServiceLog)")
    public void serviceLog() {
    }

    /**
     * 前置通知
     *
     * @param joinPoint 切点
     * @throws InterruptedException
     */
    @Before("serviceLog()")
    public void doBefore(JoinPoint joinPoint) throws InterruptedException {
        //线程绑定变量（该数据只有当前请求的线程可见）
        Date beginTime = new Date();
        System.out.println("开始时间 = " + beginTime);
        Object[] args = joinPoint.getArgs();
        Arrays.asList(args).listIterator().forEachRemaining(System.out::println);
    }
}

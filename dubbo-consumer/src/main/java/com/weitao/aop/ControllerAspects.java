package com.weitao.aop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * controller层切面
 */
@Aspect
@Component
public class ControllerAspects {
    //抽取公共的切入点表达式
    //1、本类引用
    //2、其他的切面引用
    @Pointcut("execution( * com.weitao.controller.*.*(..))")
    public void div() {
    }


    //@Before在目标方法之前切入；切入点表达式（指定在哪个方法切入）
    //JoinPoint一定要出现在参数列表的第一位
    @Before(value = "div()")
    public void logStart(JoinPoint joinpoint) {
        System.out.println("logStart>>>>" + joinpoint.getSignature().getName() + ">>>>" + Arrays.toString(joinpoint.getArgs()));
    }

  /*  @After(value = "log()")

    public void logEnd(JoinPoint joinpoint) {
        System.out.println("logEnd>>>>>" + joinpoint.getSignature().getName() + ">>>>" + Arrays.toString(joinpoint.getArgs()));
    }

    @AfterReturning(value = "execution(public int com.weitao.service.CalculatorService.*(..))", returning = "val")
    public void logReturn(Object val) {
        System.out.println("logReturn>>>>" + val);
    }

    @AfterThrowing(value = "execution(public int com.weitao.service.CalculatorService.*(..))", throwing = "object")
    public void logException(Exception object) {
        System.out.println("logException>>>>" + object);
    }

    @Around(value = "execution(public int com.weitao.service.CalculatorService.*(..))")
    public Object arroud(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("[环绕前：]");
        Object result = pjp.proceed(); // 执行目标方法
        System.out.println("[环绕后：]");
        return result;

    }
*/

}

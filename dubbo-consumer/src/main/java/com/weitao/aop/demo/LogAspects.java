package com.weitao.aop.demo;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

import java.util.Arrays;

@Aspect
public class LogAspects {
    //抽取公共的切入点表达式
    //1、本类引用
    //2、其他的切面引用
    @Pointcut("execution(public int com.weitao.aop.demo.MathCalculator.*(..))")
    private void pointCut(){};

    //@Before在目标方法之前切入；切入点表达式（指定在哪个方法切入）
    //JoinPoint一定要出现在参数列表的第一位
    @Before(value = "pointCut()")
    public void logStart(JoinPoint joinpoint) {
        System.out.println("logStart>>>>"+joinpoint.getSignature().getName()+">>>>"+ Arrays.toString(joinpoint.getArgs()));
    }

    @After(value ="pointCut()")
    public void logEnd(JoinPoint joinpoint) {
        System.out.println("logEnd>>>>>"+joinpoint.getSignature().getName()+">>>>"+Arrays.toString(joinpoint.getArgs()));
    }

    @AfterReturning(value ="execution(public int com.weitao.aop.demo.MathCalculator.*(..))",returning="val")
    public void logReturn(Object val) {
        System.out.println("logReturn>>>>"+val);
    }

    @AfterThrowing(value = "execution(public int com.weitao.aop.demo.MathCalculator.*(..))",throwing = "object")
    public void logException(Exception object) {
        System.out.println("logException>>>>"+object);
    }

    @Around(value = "execution(public int com.weitao.aop.demo.MathCalculator.*(..))")
    public Object  arroud(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("[环绕前：]");
        Object result =  pjp.proceed(); // 执行目标方法
        System.out.println("[环绕后：]");
        return result;

    }

}

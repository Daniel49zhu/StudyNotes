package com.zjc;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
public class NotVeryUsefulAspect {

    @Pointcut("execution(* com.zjc.services.*.*(..))")
    public void PointcutDeclaration() {
    }

    //前置通知,方法执行之前执行
    @Before("PointcutDeclaration()")
    public void BeforeMethod(JoinPoint jp) {
        String methodName = jp.getSignature().getName();
        Object[] args = jp.getArgs();
        System.out.println("BeforeMethod  The method   " + methodName + "   parameter is  " + Arrays.asList(args));
        System.out.println("add before");
        System.out.println();
    }
    
    @Around("execution(* com.zjc.services.HelloService.sayHello(..))")
    public void AroundMethod(ProceedingJoinPoint pjp) throws Throwable {
        String methodName = pjp.getSignature().getName();
        System.out.println("method " + methodName + " start time:" + System.currentTimeMillis());

        Object re = pjp.proceed();

        System.out.println("method " + methodName + " end time:" + System.currentTimeMillis());

    }
}

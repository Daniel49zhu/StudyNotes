package com.zjc.spring.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
public class FooAspect {
    @AfterReturning("bean(testBean*)")
    public void printAfter(JoinPoint joinPoint) throws Exception{
        ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                 .getResponse()
                 .getWriter()
                 .println(joinPoint.getTarget());
    }
}
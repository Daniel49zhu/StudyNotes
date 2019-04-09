package com.zjc.spring.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.aop.support.AopUtils;
import org.springframework.aop.support.Pointcuts;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;

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
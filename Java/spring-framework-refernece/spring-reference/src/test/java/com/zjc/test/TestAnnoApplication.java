package com.zjc.test;

import com.zjc.services.AutowiredAnnoService;
import com.zjc.services.NotRequiredService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestAnnoApplication {
    private ClassPathXmlApplicationContext context;

    @Before
    public void init() {
        context = new ClassPathXmlApplicationContext("classpath:/applicationContext.xml");
    }

    @Test
    public void test1() {
        AutowiredAnnoService autowiredAnnoService = (AutowiredAnnoService) context.getBean("autowiredAnnoService");
        autowiredAnnoService.sayHello();
    }

    @Test
    public void test2() {
        NotRequiredService notRequiredService = (NotRequiredService) context.getBean("notRequiredService");
        notRequiredService.sayHello();
    }

    @Test
    public void test3() {
        AutowiredAnnoService autowiredAnnoService = (AutowiredAnnoService) context.getBean("autowiredAnnoService");
        ApplicationContext applicationContext = autowiredAnnoService.getContext();
        Assert.assertNotNull(applicationContext);
    }
}

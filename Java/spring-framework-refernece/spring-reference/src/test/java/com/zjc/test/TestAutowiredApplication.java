package com.zjc.test;

import com.zjc.services.AutowiredAnnoService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestAutowiredApplication {
    private ClassPathXmlApplicationContext context;

    @Before
    public void init() {
        context = new ClassPathXmlApplicationContext("classpath:/spring3.xml");
    }

    @Test
    public void test1() {
        AutowiredAnnoService autowiredAnnoService = (AutowiredAnnoService) context.getBean("autowiredAnnoService");
        autowiredAnnoService.sayHello();
    }
}

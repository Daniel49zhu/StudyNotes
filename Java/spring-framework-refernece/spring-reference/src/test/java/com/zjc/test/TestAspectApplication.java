package com.zjc.test;

import com.zjc.services.HelloService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestAspectApplication {
    private ClassPathXmlApplicationContext context;

    @Before
    public void init() {
        context = new ClassPathXmlApplicationContext("classpath:/spring4.xml");
    }

    @Test
    public void test1() {
        HelloService helloService = (HelloService) context.getBean("helloService");
        helloService.sayHello();
    }
}

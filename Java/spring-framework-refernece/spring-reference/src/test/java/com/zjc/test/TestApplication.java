package com.zjc.test;

import com.zjc.services.HelloService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestApplication {
    private ClassPathXmlApplicationContext context;

    @Before
    public void init() {
        context = new ClassPathXmlApplicationContext("classpath:/spring.xml");
    }

    @Test
    public void test1() {
        HelloService helloService = (HelloService) context.getBean("helloService");
        helloService.sayHello();
    }

    @Test
    public void test2() {
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
        beanFactory.registerSingleton("helloService2",new HelloService());

        HelloService helloService2 = (HelloService) beanFactory.getBean("helloService2");
        helloService2.sayHello();
    }
}

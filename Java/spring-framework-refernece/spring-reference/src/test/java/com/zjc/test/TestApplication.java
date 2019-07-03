package com.zjc.test;

import com.zjc.services.AutowiredService;
import com.zjc.services.CoolService;
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
        beanFactory.registerSingleton("helloService2", new HelloService());

        HelloService helloService2 = (HelloService) beanFactory.getBean("helloService2");
        helloService2.sayHello();
    }

    @Test
    public void test3() {
        HelloService firstService = (HelloService) context.getBean("firstService");
        HelloService demoService = (HelloService) context.getBean("demoService");
        firstService.sayHello();
        demoService.sayHello();
        System.out.println(firstService.equals(demoService));
    }

    @Test
    public void test4() {
        HelloService helloService = (HelloService) context.getBean("helloService");
        System.out.println("Hello I'm " + helloService.getBeanOne().getName());
        System.out.println("Hello I'm " + helloService.getBeanTwo().getName());
    }

    @Test
    public void test5() {
        CoolService coolService = (CoolService) context.getBean("coolService");
        System.out.println(coolService);
    }

    @Test
    public void test6() {
        AutowiredService autowiredService = (AutowiredService) context.getBean("autowiredService");
        System.out.println("Hello I'm " + autowiredService.getBeanOne().getName());
        System.out.println("Hello I'm " + autowiredService.getBeanTwo().getName());
    }
}

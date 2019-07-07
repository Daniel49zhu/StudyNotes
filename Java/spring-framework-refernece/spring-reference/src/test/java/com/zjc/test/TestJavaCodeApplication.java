package com.zjc.test;

import com.zjc.AppConfiguration;
import com.zjc.services.AutowiredAnnoService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestJavaCodeApplication {
    private AnnotationConfigApplicationContext ctx;
    @Before
    public void config() {
        ctx =  new AnnotationConfigApplicationContext(AppConfiguration.class);
    }

    @Test
    public void test1() {
      AutowiredAnnoService autowiredAnnoService = (AutowiredAnnoService) ctx.getBean("autowiredAnnoService");
      autowiredAnnoService.sayHello();
    }
}

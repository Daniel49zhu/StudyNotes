package com.zjc.test;

import com.zjc.Configuration;
import com.zjc.services.AutowiredAnnoService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestJavaCodeApplication {
    private AnnotationConfigApplicationContext ctx;
    @Before
    public void config() {
        ctx =  new AnnotationConfigApplicationContext(Configuration.class);
    }

    @Test
    public void test1() {
      AutowiredAnnoService autowiredAnnoService = (AutowiredAnnoService) ctx.getBean("autowiredAnnoService");
      autowiredAnnoService.sayHello();
    }
}

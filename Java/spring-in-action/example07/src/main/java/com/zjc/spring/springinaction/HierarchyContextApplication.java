package com.zjc.spring.springinaction;

import com.zjc.spring.config.FooConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@SpringBootApplication
@RestController
public class HierarchyContextApplication {
    public static void main(String[] args) {
        SpringApplication.run(HierarchyContextApplication.class,args);
    }

    @RequestMapping(path="/run")
    public void run(HttpServletResponse response) throws Exception {
        PrintWriter writer = response.getWriter();

        ApplicationContext fooContext = new AnnotationConfigApplicationContext(FooConfig.class);
        ClassPathXmlApplicationContext barContext = new ClassPathXmlApplicationContext(new String[] {"applicationContext.xml"}, fooContext);

        TestBean bean = fooContext.getBean("testBeanX", TestBean.class);
        writer.println(bean.hello());

        bean = barContext.getBean("testBeanY", TestBean.class);
        writer.println(bean.hello());

        bean = barContext.getBean("testBeanX", TestBean.class);
        writer.println(bean.hello());
    }
}

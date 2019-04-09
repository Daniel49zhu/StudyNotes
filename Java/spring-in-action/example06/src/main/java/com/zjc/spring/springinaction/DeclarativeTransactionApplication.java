package com.zjc.spring.springinaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@RestController
@SpringBootApplication
public class DeclarativeTransactionApplication {
    @Autowired
    private IFooService fooService;
    @Autowired
    private JdbcTemplate jdbcTemplate;


    public static void main(String[] args) {
        SpringApplication.run(DeclarativeTransactionApplication.class, args);
    }

    @RequestMapping(path = "/run")
    public void run(HttpServletResponse response) throws Exception{
        PrintWriter writer = response.getWriter();
        fooService.insertRecord();
        writer.println("AAA {}"+jdbcTemplate.queryForObject("SELECT COUNT(*) FROM FOO WHERE BAR='AAA'", Long.class));
        try {
            fooService.insertThenRollback();
        } catch (Exception e) {
            writer.println("BBB {}"+jdbcTemplate.queryForObject("SELECT COUNT(*) FROM FOO WHERE BAR='BBB'", Long.class));
        }

        try {
            fooService.invokeInsertThenRollback();
        } catch (Exception e) {
            writer.println("BBB {}"+ jdbcTemplate.queryForObject("SELECT COUNT(*) FROM FOO WHERE BAR='BBB'", Long.class));
        }
    }
}


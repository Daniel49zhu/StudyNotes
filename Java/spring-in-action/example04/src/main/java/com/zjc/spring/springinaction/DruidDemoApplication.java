package com.zjc.spring.springinaction;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.PrintWriter;

@SpringBootApplication
@RestController
public class DruidDemoApplication {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
        SpringApplication.run(DruidDemoApplication.class, args);
    }

    @RequestMapping(path = "/run")
    public void run(HttpServletResponse response) throws Exception {
        PrintWriter writer = response.getWriter();
        writer.println(dataSource.toString());
        jdbcTemplate.queryForList("SELECT * FROM FOO").forEach(list -> writer.println(list));
        writer.close();
    }
}

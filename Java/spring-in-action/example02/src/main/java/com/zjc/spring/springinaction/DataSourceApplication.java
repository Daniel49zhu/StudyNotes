package com.zjc.spring.springinaction;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootApplication
@Slf4j
@RestController
public class DataSourceApplication {
    @Autowired
    private DataSource dataSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
        SpringApplication.run(DataSourceApplication.class, args);
    }

    @RequestMapping(path = "/run")
    public void run(HttpServletResponse response) throws Exception {
        PrintWriter writer = response.getWriter();
        showConnection(writer);
        showData(writer);
    }

    private void showConnection(PrintWriter writer) throws SQLException {
        writer.println(dataSource.toString());
        Connection conn = dataSource.getConnection();
        writer.println(conn.toString());
        conn.close();
    }

    private void showData(PrintWriter writer) {
        jdbcTemplate.queryForList("SELECT * FROM FOO").forEach(row -> writer.println(row.toString()));
    }
}

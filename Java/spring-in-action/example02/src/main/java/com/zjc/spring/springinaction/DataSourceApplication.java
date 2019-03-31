package com.zjc.spring.springinaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootApplication
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
/** output:
 * > curl http://localhost:8080/run
 * HikariDataSource (HikariPool-1)
 * HikariProxyConnection@641064040 wrapping conn0: url=jdbc:h2:mem:testdb user=SA
 * {ID=1, BAR=aaa}
 * {ID=2, BAR=bbb}
 */

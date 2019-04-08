package com.zjc.spring.springinaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@SpringBootApplication
@RestController
public class TransactionApplication {
    @Autowired
    private TransactionTemplate transactionTemplate;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
        SpringApplication.run(TransactionApplication.class, args);
    }

    @RequestMapping(path = "/run")
    public void run(HttpServletResponse response) throws Exception {
        PrintWriter writer = response.getWriter();
        writer.println("COUNT BEFORE TRANSACTION: {}" + getCount());
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                jdbcTemplate.execute("INSERT INTO FOO (ID, BAR) VALUES (1, 'aaa')");
                writer.println("COUNT IN TRANSACTION: {}" + getCount());
                transactionStatus.setRollbackOnly();
            }
        });
        writer.println("COUNT AFTER TRANSACTION: {}" + getCount());
    }

    private long getCount() {
        return (long) jdbcTemplate.queryForList("SELECT COUNT(*) AS CNT FROM FOO")
                .get(0).get("CNT");
    }
}

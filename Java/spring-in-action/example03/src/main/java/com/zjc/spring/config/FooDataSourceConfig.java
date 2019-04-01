package com.zjc.spring.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Configuration
public class FooDataSourceConfig {

//    @Bean
//    @ConfigurationProperties(prefix="foo.datasource")
//    public DataSource fooDataSource() {
//        return DataSourceBuilder.create().build();
//    }
//
//    @Bean
//    @Resource
//    public PlatformTransactionManager fooTxManager(DataSource fooDataSource) {
//        return new DataSourceTransactionManager(fooDataSource);
//    }
}

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
public class BarDataSourceConfig {


//    @Bean
//    @Primary
//    @ConfigurationProperties("bar.datasource")
//    public DataSource barDataSource() {
//        return DataSourceBuilder.create().build();
//    }
//
//    @Bean
//    @Resource
//    @Primary
//    public PlatformTransactionManager barTxManager(DataSource barDataSource) {
//        return new DataSourceTransactionManager(barDataSource);
//    }
}

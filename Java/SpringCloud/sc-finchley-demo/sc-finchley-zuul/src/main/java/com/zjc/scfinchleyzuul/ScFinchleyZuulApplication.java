package com.zjc.scfinchleyzuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
/**
 * 
 * @return
 * @author makoto
 * @date 2020/2/29 14:47
 */
@SpringBootApplication
@EnableZuulProxy
@EnableEurekaClient
public class ScFinchleyZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScFinchleyZuulApplication.class, args);
    }

}

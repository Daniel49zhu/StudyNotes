package com.zjc.scfinchleyclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 *
 * @return
 * @author makoto
 * @date 2020/2/26 22:21
 */
@SpringBootApplication
@EnableEurekaClient
public class ScFinchleyClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScFinchleyClientApplication.class, args);
    }

}

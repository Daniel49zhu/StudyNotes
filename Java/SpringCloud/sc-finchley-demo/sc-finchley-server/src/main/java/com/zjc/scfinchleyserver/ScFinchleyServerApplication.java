package com.zjc.scfinchleyserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 *
 * @return
 * @author makoto
 * @date 2020/2/26 21:44
 */
@SpringBootApplication
@EnableEurekaServer
public class ScFinchleyServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScFinchleyServerApplication.class, args);
    }

}

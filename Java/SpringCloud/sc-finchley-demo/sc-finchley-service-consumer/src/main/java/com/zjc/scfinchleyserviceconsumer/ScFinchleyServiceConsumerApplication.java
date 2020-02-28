package com.zjc.scfinchleyserviceconsumer;

import com.zjc.MyRibbonRuleConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

/**
 * @author makoto
 * @return
 * @date 2020/2/27 21:00
 */
@SpringBootApplication
@EnableDiscoveryClient
@RibbonClient(name = "SC-FINCHLEY-CLIENT-ONE", configuration = MyRibbonRuleConfig.class)
public class ScFinchleyServiceConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScFinchleyServiceConsumerApplication.class, args);
    }

}

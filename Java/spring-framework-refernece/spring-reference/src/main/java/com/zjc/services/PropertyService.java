package com.zjc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:/config.properties")
public class PropertyService {
    @Autowired
    private Environment env;

    @Value("${server.port}")
    private String port;

    public void sayHello() {
        System.out.println(env.getProperty("server.name"));
        System.out.println(port);
    }
}

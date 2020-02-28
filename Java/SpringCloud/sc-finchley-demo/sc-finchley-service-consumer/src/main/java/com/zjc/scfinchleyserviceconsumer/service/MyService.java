package com.zjc.scfinchleyserviceconsumer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @return
 * @author makoto
 * @date 2020/2/27 21:12
 */
@Service
public class MyService {

    @Autowired
    private RestTemplate restTemplate;

    public String hiService(String name) {
        return restTemplate.getForObject("http://SC-FINCHLEY-CLIENT-ONE/hi?name="+name,String.class);
    }
}

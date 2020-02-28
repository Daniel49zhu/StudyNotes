package com.zjc.scfinchleyserviceconsumer.web;

import com.zjc.scfinchleyserviceconsumer.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@RestController
public class HelloControler {

    @Autowired
    private MyService myService;

    @GetMapping(value = "/hi")
    public String hi(@RequestParam String name) {
        return myService.hiService( name );
    }
}
package com.zjc.scfinchleyclient.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @return
 * @author makoto
 * @date 2020/2/26 22:21
 */
@RestController
public class MyController {

    @Value("${server.port}")
    String port;

    @RequestMapping(path="/hi")
    public String home(@RequestParam(value="name",defaultValue="null")String name) {
        return "hi " + name + ", I am from port:" + port;
    }
}

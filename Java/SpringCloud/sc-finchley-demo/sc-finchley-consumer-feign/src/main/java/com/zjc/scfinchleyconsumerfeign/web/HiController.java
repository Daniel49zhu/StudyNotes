package com.zjc.scfinchleyconsumerfeign.web;

import com.zjc.scfinchleyconsumerfeign.service.SchedualServiceHi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
/**
 *
 * @return
 * @author makoto
 * @date 2020/2/28 21:43
 */
@RestController
public class HiController {

    /**
     * (编译器报错，无视。 因为这个Bean是在程序启动的时候注入的，编译器感知不到，所以报错。)
     * @return
     * @author makoto
     * @date 2020/2/28 21:43
     */
    @Autowired
    SchedualServiceHi schedualServiceHi;

    @GetMapping(value = "/hi")
    public String sayHi(@RequestParam String name) {
        return schedualServiceHi.sayHiFromClientOne(name);
    }
}
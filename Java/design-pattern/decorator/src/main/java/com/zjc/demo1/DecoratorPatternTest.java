package com.zjc.demo1;

import com.zjc.demo1.component.Beverage;
import com.zjc.demo1.component.DarkRoast;
import com.zjc.demo1.component.Decaf;
import com.zjc.demo1.decorator.Mocha;
import com.zjc.demo1.decorator.Whip;

public class DecoratorPatternTest {
    public static void main(String[] args) {
        Beverage beverage = new Decaf();//这是顾客要的脱因咖啡
        beverage = new Mocha(beverage);//顾客想要摩卡，用摩卡装饰
        beverage = new Whip(beverage);//顾客想要奶泡，用奶泡装饰
        System.out.println(beverage.getDescription() + " 共花费$" + beverage.cost());

        Beverage beverage2 = new DarkRoast();//这是顾客要的深培咖啡
        beverage2 = new Mocha(beverage2);//顾客想要摩卡，用摩卡装饰
        beverage2 = new Mocha(beverage2);//顾客想要摩卡，用摩卡装饰
        beverage2 = new Whip(beverage2);//顾客想要奶泡，用奶泡装饰
        System.out.println(beverage2.getDescription() + " 共花费$" + beverage2.cost());

        @SpringBootApplication
        @RestController
        public class Application {

            @RequestMapping("/")
            public String home() {
                return "Hello world";
            }

            public static void main(String[] args) {
                new SpringApplicationBuilder(Application.class).web(true).run(args);
            }


    }
}


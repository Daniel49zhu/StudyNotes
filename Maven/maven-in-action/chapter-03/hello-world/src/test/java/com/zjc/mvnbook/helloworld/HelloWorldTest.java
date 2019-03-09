package com.zjc.mvnbook.helloworld;

import org.junit.Assert;
import org.junit.Test;

public class HelloWorldTest {
    @Test
    public void testSayHello() {
        HelloWorld helloWorld = new HelloWorld();
        String result = helloWorld.sayHello();
        Assert.assertSame("Hello Maven",result);
    }
}

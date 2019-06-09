package com.zjc;


import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TestBeanUtils {

    @Test
    public void testCloneBean() throws Exception {
        Person p1 = new Person();
        p1.setAge(12);
        p1.setBirth(new Date(0));
        p1.setName("John");
        Person p2 = (Person)BeanUtils.cloneBean(p1);

        System.out.println(p1);
        System.out.println(p2);
    }

    @Test
    public void testCopyProperties() throws Exception {
        Person p1 = new Person();
        p1.setAge(12);
        p1.setBirth(new Date(0));
        p1.setName("John");
        p1.setHobby(new ArrayList<String>() {{
            this.add("singing");
            this.add("dancing");
            this.add("rap");
            this.add("basketball");
        }});
        Person p2 = new Person();
        Person p3 = new Person();
        BeanUtils.copyProperties(p2,p1);
        BeanUtils.copyProperty(p3,"age",12);

        System.out.println(p1);
        System.out.println(p2);
        System.out.println(p3);

        System.out.println(BeanUtils.describe(p1));
        System.out.println("---------------------------------------");
        System.out.println(BeanUtils.getProperty(p1,"hobby[0]"));

        Map<String,Object> properties = new HashMap<>();
        properties.put("name","pasive");
        properties.put("age",13);
        properties.put("birth",new Date(2452435243L));
        BeanUtils.populate(p3,properties);
        System.out.println(p3);
    }
}

package com.zjc;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class TestDemo {
    public static void main(String[] args) {
//        Map<String, String> map = new LinkedHashMap<>(1 << 4, 0.75f, true);
//        map.put("name", "tom");
//        map.put("age", "34");
//        map.put("address", "beijing");
//
//        //既然演示访问顺序，我们就访问其中一个元素，这里只是打印一下
//        System.out.println("我是被访问的元素：" + map.get("age"));
//
//        Iterator iterator = map.entrySet().iterator();
//        while (iterator.hasNext()) {
//            Map.Entry entry = (Map.Entry) iterator.next();
//            String key = (String) entry.getKey();
//            String value = (String) entry.getValue();
//            System.out.printf("Key = %s,Value = %s\n", key, value);
//        }


        //新建一个默认的构造函数，默认是按照插入顺序保存
//        QLinkedHashMap<String,String> map = new QLinkedHashMap<>();
//        map.put("name","tom");
//        map.put("age","32");
//        map.put("address","beijing");
//        //验证是不是按照插入的顺序打印
//        QLinkedHashMap.QIterator iterator =  map.iterator();
//        while (iterator.hasNext()){
//            QLinkedHashMap.QEntry e = iterator.next();
//            System.out.println("key=" + e.key + " value=" + e.value);
//        }


        double v=  5.0 + 0.1 + 0.1;
        System.out.println(v);
    }
}

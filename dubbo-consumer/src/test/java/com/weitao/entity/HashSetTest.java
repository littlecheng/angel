package com.weitao.entity;

import java.util.*;

public class HashSetTest {
    public static void main(String[] args) {
        Set set = new HashSet<String>();
        set.add("ok");
        set.add("ok");
        set.add("no");
        set.add(null);
        System.out.println(set.size());
        System.out.println(set.contains("ok"));

        Set set1 = new HashSet<String>();
        set1.add("ok");
        set1.add("no");

        System.out.println(set.equals(set1));

        Iterator<String> iter = set.iterator();
        while (iter.hasNext()){
            String value = iter.next();
            System.out.println(value );
        }

        System.out.println("-----------------------hashmap-------------------");

        HashMap<String,String> map = new HashMap<>();
        map.put("ok1","wewe");
        map.put("ok","34345");
        map.put(null,"ererer");
        map.put(null,null);



        //输出方式1
        Iterator<String> mapiter = map.values().iterator();
        while (mapiter.hasNext()){
            System.out.println( "value = "+mapiter.next() );
        }
        System.out.println();

        //输出方式2
        Iterator<Map.Entry<String,String>>  mapiter2 = map.entrySet().iterator();
        while (mapiter2.hasNext()){
            Map.Entry<String,String> entry = mapiter2.next();
            System.out.println("key="+entry.getKey()+"\t value = "+entry.getValue() );
        }
        System.out.println();
        //输出方式3

        Iterator<String> iterator3 = map.keySet().iterator();
        while(iterator3.hasNext()){
            String key = iterator3.next();
            System.out.println("key="+key+"\t value = "+map.get(key) );
        }
    }
}

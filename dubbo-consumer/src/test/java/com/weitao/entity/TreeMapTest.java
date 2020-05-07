package com.weitao.entity;

import java.util.TreeMap;

public class TreeMapTest {
    public static void main(String[] args) {
        TreeMap map = new TreeMap();
        map.put("v","aa");
        map.put("b","aa");
    //    map.put("c","bb");
        map.put("c","ddd");
        System.out.println(map.size());
        System.out.println(map.get("c"));
        System.out.println();
    }
}

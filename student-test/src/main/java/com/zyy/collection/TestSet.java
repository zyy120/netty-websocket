package com.zyy.collection;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class TestSet {

    public static void main(String[] args) {
        Set hasSet=new HashSet();
        hasSet.add(2999);
        hasSet.add(Integer.valueOf(2999));
        for (Object o : hasSet) {
            System.out.println(o.toString()+"::"+o.hashCode());
        }
        Set set=new LinkedHashSet();

        set.add(Integer.valueOf(1));
        set.add(Integer.valueOf(1));

        for (Object o : set) {
            System.out.println(o.toString());
        }

        Set treeSet=new TreeSet();

        Map<String,Object> s=new ConcurrentHashMap<>();
    }
}

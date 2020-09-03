package com.zn.juc.timeutil.Collection;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class HashMapTest {
    public static void main(String[] args) {
        // 异步处理
        Map<String, String> concurrentHashMap = new ConcurrentHashMap<>();
        for (int x = 0; x < 2; x++) {
            int temp = x;
            new Thread(() -> {
                for (int y = 0; y < 5; y++) {
                    concurrentHashMap.put(Thread.currentThread().getName(), "值：x=" + temp + ",y=" + y);
                    System.out.println(concurrentHashMap.toString());
                }
            }).start();
        }
    }
}

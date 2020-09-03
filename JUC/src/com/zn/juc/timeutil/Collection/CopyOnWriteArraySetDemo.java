package com.zn.juc.timeutil.Collection;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public class CopyOnWriteArraySetDemo {
    public static void main(String[] args) throws Exception {
        // 异步处理
        Set<String> all = new CopyOnWriteArraySet<>();
        int one = 2;
        int two = 10;
        int numS= one *two ;
        for (int x = 0; x < one; x++) {
            int temp = x;
            new Thread(() -> {
                for (int y = 0; y < two; y++) {
                    all.add(Thread.currentThread().getName() + " - " + temp + " - " + y);
                    System.out.println(all);
                }
            }).start();
        }
    }
}

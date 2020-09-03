package com.zn.juc.timeutil.CAS;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Volatile_demo {

    public static AtomicInteger num = new AtomicInteger(0);
//    public static volatile int num =0;

    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
//                try {
//                    Thread.sleep(1);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                for (int j = 0; j < 10000; j++) {
                        num.incrementAndGet();
//                    num++;
                }
            }, "线程-" + i).start();
        }

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("num:" + num);
    }
}

package com.zn.juc.timeutil.threadfactory;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

public class MLDNTestDemo {
    public static void main(String[] args) throws Exception {
        new SimpleThreadFactory().newThread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName()+",i="+i);
            }
        }).start();
    }
}

class SimpleThreadFactory implements ThreadFactory {

    @Override
    public Thread newThread(Runnable r) {
        AtomicLong count = new AtomicLong(0);
        return new Thread(r,"线程："+count.getAndIncrement());
    }
}

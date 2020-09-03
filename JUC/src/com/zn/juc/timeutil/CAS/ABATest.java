package com.zn.juc.timeutil.CAS;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ABATest {
    public static AtomicInteger atomicInteger = new AtomicInteger(0);

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean i = atomicInteger.compareAndSet(0,2);
            System.out.println(atomicInteger.get());
            System.out.println("主线程的cas操作是否成功："+i);

        },"主线程").start();

        new Thread(() -> {
            int i = atomicInteger.incrementAndGet();
            int i1 = atomicInteger.decrementAndGet();
            System.out.println("i1:"+i1);

        },"干扰线程").start();
    }
}

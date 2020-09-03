package com.zn.juc.timeutil;

import java.util.concurrent.locks.LockSupport;

public class LockSupportDemo {
    private static String msg = null;

    public static void main(String[] args) {
        Thread thread = Thread.currentThread();
        String name = thread.getName();
        System.out.println(name);

        new Thread(() -> {
            try {
                msg="www.baidu.com";
            }finally {
                LockSupport.unpark(thread);
            }
        }).start();
        LockSupport.park(thread);
        System.out.println(msg);
    }
}

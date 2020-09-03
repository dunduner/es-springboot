package com.zn.juc.timeutil.LockSupport;

import java.util.concurrent.locks.LockSupport;

public class MLDNTestDemo {
    public static String msg = null;  // 设置一个字符串

    public static void main(String[] args) throws Exception {

        // 获得当前的线程操作类
        Thread mainThread = Thread.currentThread();

        new Thread(() -> {
            try {
                msg = "www.baidu.com";
            } finally {  // 解锁关起状态
                LockSupport.unpark(mainThread);//恢复
            }
        }).start();
        LockSupport.park(mainThread);//  挂起
        System.out.println("********** 主线程执行完毕,msg=" + msg);
    }
}

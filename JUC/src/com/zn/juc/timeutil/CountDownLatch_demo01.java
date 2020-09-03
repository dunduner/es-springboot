package com.zn.juc.timeutil;

import java.util.concurrent.CountDownLatch;

public class CountDownLatch_demo01 {
    public static void main(String[] args) throws InterruptedException {

//        for (int i = 0; i <51 ; i++) {
////            Thread thread = new Thread(new Runnable() {
////                @Override
////                public void run() {
////                    System.out.println(Thread.currentThread().getName()+"线程执行结束！");
////                }
////            });
////            thread.start();
////        }
////
////        System.out.println("主线程执行完毕！");

        CountDownLatch countDownLatch = new CountDownLatch(10);//启动十次计数器
        for (int x = 0; x < 10; x++) {
            new Thread(() -> {
                System.out.println("【"+Thread.currentThread().getName()+"】线程应用执行完毕。");
                countDownLatch.countDown();//每次线程执行完毕就 减少计数器一次
                long count = countDownLatch.getCount();
                System.out.println(count);//获取当前线程计数数字
            },"线程对象-" + x).start();
        }
        countDownLatch.await();//等待10个线程运行完毕后  执行后续操作

        System.out.println("【*** 主线程 ***】所有的程序执行完毕。");
    }
}

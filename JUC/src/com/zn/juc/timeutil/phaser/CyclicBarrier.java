package com.zn.juc.timeutil.phaser;

import java.util.Random;

/**
 * @author zhangning
 * @date 2020/8/12
 */
public class CyclicBarrier {
    public static void main(String[] args) throws Exception{
        int num=6;
        java.util.concurrent.CyclicBarrier cyclicBarrier=new java.util.concurrent.CyclicBarrier(num, new Runnable() {
            @Override
            public void run() {
                System.out.println("========================");
            }
        });
        Random random=new Random();
        Runnable task=new Runnable() {
            @Override
            public void run() {
                try {
                    cyclicBarrier.await();
                    System.out.println(Thread.currentThread().getName()+" start,time->"+System.currentTimeMillis());
                    Thread.sleep(random.nextInt(4000));
                    System.out.println(Thread.currentThread().getName()+" 结束,time->"+System.currentTimeMillis());
                    cyclicBarrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        for(int i=0;i<num-1;i++){
            new Thread(task).start();
        }

        System.out.println("main thread start await,time->"+System.currentTimeMillis());
        cyclicBarrier.await();
        System.out.println("all thread start,time->"+System.currentTimeMillis());
        cyclicBarrier.await();
        System.out.println("main thread end,time->"+System.currentTimeMillis());

    }
}

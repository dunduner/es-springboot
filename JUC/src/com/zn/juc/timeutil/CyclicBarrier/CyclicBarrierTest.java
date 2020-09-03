package com.zn.juc.timeutil.CyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {

    public static void main(String[] args) {

        // 当凑够2个线程就进行触发
        CyclicBarrier cb = new CyclicBarrier(10, new Runnable() {
            @Override
            public void run() {
                //这个参数  就是人数满了。触发的
                System.out.println("人满发车!");

            }
        });
        for (int x = 1; x <= 10; x++) {
            int sec = x;
            new Thread(() -> {

                try {

                    if(sec==10){
                        System.out.println("等第十个人");
                        Thread.sleep(5000);
                        System.out.println("【" + Thread.currentThread().getName() + " - 等待开始】");
                    }else{
                        System.out.println("【" + Thread.currentThread().getName() + " - 等待开始】");
                    }
                    cb.await();
                    System.out.println("终于等到了");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }

//                try {
//                    if (sec == 2 || sec == 4) {    // 重置
//                        cb.reset();    // 重置
//                        System.out.println("【重置处理】 ****** getNumberWaiting:" + cb.getNumberWaiting());
//                    } else {
//                        TimeUnit.SECONDS.sleep(sec);
//                        cb.await(6, TimeUnit.SECONDS); // 6秒达不到就报错
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                System.out.println("【" + Thread.currentThread().getName() + " - 等待结束】");
            }, "玩家-" + x).start();
        }

    }
}

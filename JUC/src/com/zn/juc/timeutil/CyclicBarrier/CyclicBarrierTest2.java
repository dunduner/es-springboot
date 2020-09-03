package com.zn.juc.timeutil.CyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest2 {

    public static void main(String[] args) {

        // 当凑够2个线程就进行触发
        CyclicBarrier cb = new CyclicBarrier(3, new Runnable() {
            @Override
            public void run() {
                //这个参数  就是人数满了。触发的
                System.out.println("人满发车!");

            }
        });
        for (int x = 1; x <= 3; x++) {
            int sex =x ;
            new Thread(() -> {
                try {
                    System.out.println("【" + Thread.currentThread().getName() + " - 去执行某操作"+(3000*sex)+"秒】");
                    Thread.sleep((3000*sex));
                    System.out.println("【" + Thread.currentThread().getName() + " - 执行完毕,等其他人】");
                    cb.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }, "玩家-" + x).start();
        }

    }
}

package com.zn.juc.timeutil.phaser;

import java.util.concurrent.Phaser;

/**
 * @author zhangning
 * @date 2020/8/12
 */
public class PhaserToCountdownLatch {

    public static void main(String[] args) {
        int num = 6;
        Phaser phaser = new Phaser(num);//表示执行任务的阶段，初始值是0，每一次advance都会将该值加1

        for (int i = 0; i < num; i++) {
            new Thread(() -> {
                try {
                    Thread.sleep(2000);
                    System.out.println(Thread.currentThread().getName() + " exit,time->" + System.currentTimeMillis());
                    //表示当前线程已到达
                    phaser.arrive();//每一次advance都会将该值加1，6个线程都达到了，推到结束
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        System.out.println("main thread start await,time->" + System.currentTimeMillis());
        //等待其他线程都到达
        phaser.awaitAdvance(phaser.getPhase());
        System.out.println("main thread end,time->" + System.currentTimeMillis());
    }
}

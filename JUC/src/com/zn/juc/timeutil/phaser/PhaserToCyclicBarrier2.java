package com.zn.juc.timeutil.phaser;

import java.util.Random;
import java.util.concurrent.Phaser;

/**
 * @author zhangning
 * @date 2020/8/12
 */

public class PhaserToCyclicBarrier2 {
    public static void main(String[] args) throws Exception {
        int num=6;
        Phaser phaser=new Phaser(num);
        Random random=new Random();
        Runnable task=new Runnable() {
            @Override
            public void run() {
                try {
                    //getUnarrivedParties等于1时，当前线程就是最后一个达到的线程
                    if(phaser.getUnarrivedParties()==1){
                        System.out.println(Thread.currentThread().getName()+" last arrive,time->"+System.currentTimeMillis());
                    }
                    phaser.arriveAndAwaitAdvance();
                    System.out.println(Thread.currentThread().getName()+" start,time->"+System.currentTimeMillis());
                    Thread.sleep(random.nextInt(1000));
                    System.out.println(Thread.currentThread().getName()+" 结束,time->"+System.currentTimeMillis());
                    if(phaser.getUnarrivedParties()==1){
                        System.out.println(Thread.currentThread().getName()+" last arrive,time->"+System.currentTimeMillis());
                    }
                    phaser.arrive();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        for(int i=0;i<num;i++){
            new Thread(task).start();
        }
        System.out.println("main thread start await,time->"+System.currentTimeMillis());
        phaser.awaitAdvance(phaser.getPhase());
        System.out.println("all thread start,time->"+System.currentTimeMillis());
        phaser.awaitAdvance(phaser.getPhase());
        System.out.println("main thread end,time->"+System.currentTimeMillis());

    }
}

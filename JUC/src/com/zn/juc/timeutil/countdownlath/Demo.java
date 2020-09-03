package com.zn.juc.timeutil.countdownlath;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;

public class Demo {

/*对于此时应该保证所有的线程执行完毕后再进行程序的输出计算，
就好比：旅游团集合人员乘车离开，因该保证所有的线程都执行完毕了(指定个数的线程),这样的话就必须做一个计数处理。*/
    public static void main(String[] args) throws Exception {
        //指定原子数量
        AtomicLong peopel = new AtomicLong(10);
        CountDownLatch countDownLatch = new CountDownLatch(peopel.intValue());
        for (int x = 1; x <= peopel.intValue(); x++) {
            new Thread(() -> {
                System.out.println("【"+Thread.currentThread().getName()+"】线程应用执行完毕。");
                countDownLatch.countDown();// 减少等待的线程个数
            },"线程对象-" + x).start();
        }
        countDownLatch.await();//;门栓10次倒数没有了，发车
        System.out.println("【*** 主线程 ***】所有的程序执行完毕。");
    }
}

package com.zn.juc.timeutil.Semaphore;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class BankPaidui {
    public static void main(String[] args) throws Exception {
        // 现在允许操作的资源一共有2两个
        Semaphore semaphore = new Semaphore(2);


        // 模拟每一个用户办理业务的时间
        Random rand = new Random();
        for (int x = 0; x < 10; x++) {
            // 每一个线程就是一个要办理业务的人员
            new Thread(() -> {
                // 现在有空余窗口
                if (semaphore.availablePermits() > 0) {
                    System.out.println("【" + Thread.currentThread().getName() + "】进入银行，此时银行没有人排队");
                } else {   // 没有空余位置
                    System.out.println("【" + Thread.currentThread().getName() + "】排队等待办理业务。");
                }
                try {
                    // 从信号量中获得操作许可
                    semaphore.acquire();
                    System.out.println("【" + Thread.currentThread().getName() + "】｛start｝开始办理业务。");
                    // 模拟办公延迟
                    TimeUnit.SECONDS.sleep(rand.nextInt(10));
                    System.out.println("【" + Thread.currentThread().getName() + "】｛end｝结束办理业务。");
                    // 当前线程离开办公窗口
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "顾客-" + x).start();
        }
    }

}

package com.zn.juc.timeutil.Queue.SynchronousQueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class SynchronousQueueTest {
    private static int num = 0;

    public static void main(String[] args) throws Exception {
        // 允许保存5个数据队列
        BlockingQueue<String> queue = new SynchronousQueue<String>();
        for (int x = 0; x < 3; x++) {
            new Thread(() -> {
                for (int y = 0; y < 5; y++) {
                    try {
                        String str = "【生产数据｛" + Thread.currentThread().getName() + "｝】y = " + (++num);
                        queue.put(str);    // 会进入到生产的阻塞状态
                        System.out.println(str);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, "生产者-" + x).start();
        }
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                while (true) {
                    try {
                        TimeUnit.SECONDS.sleep(10);
                        System.out.println(Thread.currentThread().getName() + "消费了：】" + queue.take());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, "消费者-"+i).start();
        }
    }
}

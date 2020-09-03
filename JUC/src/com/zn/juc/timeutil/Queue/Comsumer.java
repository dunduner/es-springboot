package com.zn.juc.timeutil.Queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class Comsumer {
    private BlockingQueue queue;

    public Comsumer(BlockingQueue queue) {
        this.queue = queue;
    }

    public  void xiaofie(){
        //模拟出5个线程消费
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                while (true){
                    if(queue.isEmpty()){
                        //如果空的话 结束循环
                        break;
                    }
                    try {
                        System.out.println(Thread.currentThread().getName()+"------------------{消费数据}|内容为："+queue.take());
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            },"消费者-"+i).start();
        }
    }
}

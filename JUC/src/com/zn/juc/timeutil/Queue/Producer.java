package com.zn.juc.timeutil.Queue;

import java.util.concurrent.BlockingQueue;

public class Producer {
    private BlockingQueue queue;

    public Producer(BlockingQueue queue) {
        this.queue = queue;
    }

    public void shengchan(){
        //模拟出3个线程生产数据
        for (int i = 0; i <3 ; i++) {
            new Thread(() -> {
                //每人生产5条数据
                for (int j = 0; j < 5; j++) {
                    String data = Thread.currentThread().getName()+"{生产数据}"+j;
                    try {
                        queue.put(data);//
                        System.out.println(data);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            },"生产者-"+i).start();
        }
    }
}

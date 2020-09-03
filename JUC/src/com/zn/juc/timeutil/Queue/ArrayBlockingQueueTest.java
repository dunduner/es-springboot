package com.zn.juc.timeutil.Queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ArrayBlockingQueueTest {
    public static void main(String[] args) {
      BlockingQueue<String> queue = new ArrayBlockingQueue<>(15);
//        BlockingQueue<String> queue   = new LinkedBlockingQueue<String>(20);

        Producer producer = new Producer(queue);
        producer.shengchan();


        Comsumer comsumer = new Comsumer(queue);
        comsumer.xiaofie();
    }
}

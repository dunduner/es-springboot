package com.zn.juc.timeutil.LockSupport;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhangning
 * @date 2020/8/12
 */
public class MyContainer<T> {
    private final LinkedList<T> list = new LinkedList<T>();
    private final int MAX = 10;

    private int count = 0;

    private Lock reentrantLock = new ReentrantLock();
    private Condition provider = reentrantLock.newCondition();
    private Condition consumer = reentrantLock.newCondition();


    public void put(T t) {
        try {
            reentrantLock.lock();
            while (list.size() == MAX) {//最多发10个 慢了 就休息，让消费者去消费
                provider.await();
                System.out.println("产能过剩 10个了 满库存了。通知消费者去吃");
            }
            list.add(t);
            ++count;
            consumer.signalAll();//通知消费者去消费
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            reentrantLock.unlock();
        }
    }

    public T get() {
        T t = null;
        try {
            reentrantLock.lock();
            while (list.size() == 0) {
                System.out.println("没得吃了  让消费者 休息休息");
                consumer.await();//没得吃了  让消费者 休息休息
            }
            t = list.removeFirst();
            count--;
            provider.signalAll();//通知消费者去消费

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            reentrantLock.unlock();
        }
        return t;
    }


    public static void main(String[] args) {
        MyContainer<String> myContainer = new MyContainer<>();

        //10个线程，每个线程取5个内容
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                System.out.println("消费掉的内容：" + myContainer.get());
            }, "消费者" + i).start();
        }
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //2个线程 每个线程产5个  -共10个
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                for (int j = 0; j < 5; j++) {
                    System.out.println("生产的包子："+Thread.currentThread().getName() + "----" + j);
                    myContainer.put(Thread.currentThread().getName() + "----" + j);
                }
            }, "p" + i).start();
        }

    }

}

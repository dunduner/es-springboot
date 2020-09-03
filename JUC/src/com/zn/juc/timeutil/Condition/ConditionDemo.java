package com.zn.juc.timeutil.Condition;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionDemo {
    public static String msg = "old msg";  // 设置一个字符串

    public static void main(String[] args) throws Exception {
        // 实例化Lock接口对象
        Lock myLock = new ReentrantLock();
        // 创建一个新的Condition接口对象
        Condition condition = myLock.newCondition();
        // 如果现在不进行锁定，那么Condition无法执行等待处理机制，会出现“IllegalMonitorStateException”
        myLock.lock(); // 现在是在主线程之中执行了一个lock()处理
        try {
            new Thread(() -> {
                myLock.lock();
                try {
                    msg = "www.baidu.com";
                    condition.signal(); // 唤醒等待的Condition,唤醒单个线程
                    System.out.println("线程:"+Thread.currentThread().getName());
                } finally {
                    myLock.unlock();
                }
            },"赋值线程").start();
            System.out.println("线程等待,等待线程完毕");
            condition.await(); // 线程等待
            System.out.println("****主线程执行完毕，msg = " + msg);
        } finally {
            myLock.unlock();   // 解除阻塞状态
        }
    }

}

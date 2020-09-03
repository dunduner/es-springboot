package com.zn.juc.timeutil;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MLDNTestDemo {
    public static void main(String[] args) throws Exception {
        ticket ticket = new ticket();
        for (int x = 0 ; x < 2 ; x ++) {
            new Thread(() -> {
                while (true) {
                    ticket.sale();     // 卖票处理
                }
            }).start();
        }
    }
}

class ticket{
    private Lock reentrantLock = new ReentrantLock();
    private int count  =100;

    public void sale(){
        reentrantLock.lock();
        try {
            if(count>0){
                System.out.println(Thread.currentThread().getName()+"，卖票，ticket="+this.count--);
            }
        }finally {
            reentrantLock.unlock();
        }
    }
}

package com.zn.juc.timeutil.ReentrantLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Ticket {
     Lock reentrantLock = new ReentrantLock();
    //共100张票
    private int ticketNum ;

    public Ticket(int ticketNum) {
        this.ticketNum = ticketNum;
    }

    //卖票的方法
    public void  sale() {
        reentrantLock.lock();//进入到阻塞状态，一直到unlock执行后解除阻塞
        try {
            if (ticketNum>0){
                System.out.println(Thread.currentThread().getName()+"卖的是第"+(ticketNum--)+"票");
            }
        } finally {
            reentrantLock.unlock();
        }
    }
}

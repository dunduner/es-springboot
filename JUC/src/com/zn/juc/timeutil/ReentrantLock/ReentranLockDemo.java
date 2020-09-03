package com.zn.juc.timeutil.ReentrantLock;

import java.util.concurrent.TimeUnit;

public class ReentranLockDemo {
    public static void main(String[] args) {
        int ticketNum =5;
        Ticket ticket = new Ticket(ticketNum);

        for (int x = 0 ; x < 2 ; x ++) {
            new Thread(() -> {
                int yupiao = ticketNum;
                while (yupiao>0) {
                    ticket.sale();// 卖票处理
                    yupiao--;
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}


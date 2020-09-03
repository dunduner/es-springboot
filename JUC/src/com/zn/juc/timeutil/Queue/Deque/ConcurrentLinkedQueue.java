package com.zn.juc.timeutil.Queue.Deque;

import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * @author zhangning
 * @date 2020/9/2
 */
public class ConcurrentLinkedQueue {


    static ConcurrentLinkedDeque<String> tickets = new ConcurrentLinkedDeque<String>();


    static {
        for (int i = 0; i < 10; i++) {
            tickets.add("ticketNum:" + i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
               while (true){
                   String s =  tickets.poll();
                   if(s==null){
                       break;
                   }else{
                       System.out.println("sale--"+s);
                   }
               }
            }).start();
        }

    }
}

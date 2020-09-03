package com.zn.juc.timeutil.Queue.Deque;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class LinkedBlockingDequeTest {

    public static void main(String[] args) throws Exception {
        // 利用链表来实现
        BlockingDeque<String> deque =   new LinkedBlockingDeque<>();
        deque.addFirst("Hello-First1");
        deque.addFirst("World-First2");
        deque.addLast("Hello-Last3");
        deque.addLast("World-Last4");
        System.out.println(deque.takeFirst());
        System.out.println(deque.takeFirst());
        System.out.println(deque.takeFirst());
        System.out.println(deque.takeFirst());
    }
}

package com.zn.juc.timeutil.Collection;

import java.util.concurrent.CopyOnWriteArrayList;

public class ArrayListTest {
    public static void main(String[] args) {
        // 异步处理
        //该异常主要指的是当你保存的容量个数和你的实际操作数可能不匹配的时候就会出现此异常。
//        List<String> all = new ArrayList<>();

        //使用jdk8提供的 新的集合类 就可以解决高并发时候的问题
        CopyOnWriteArrayList<String> all = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 20; i++) {
            int finalI = i;
            new Thread(() -> {
                for (int j = 0; j < 30; j++) {
                    all.add(Thread.currentThread().getName()+"-"+ finalI +"-"+j);
                }
            }).start();
        }
        System.out.println(all);

    }
}

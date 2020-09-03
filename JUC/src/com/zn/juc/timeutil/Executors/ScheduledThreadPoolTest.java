package com.zn.juc.timeutil.Executors;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPoolTest {
    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);
        for (int i = 0; i <5; i++) {
//            scheduledExecutorService.schedule(() -> {
//                System.out.println(Thread.currentThread().getName()+"执行任务");
//            },2, TimeUnit.SECONDS);//延迟2秒后启动

            //有间隔的线程池  5秒后开始job，然后每3秒做一次工作
            scheduledExecutorService.scheduleAtFixedRate(() -> {
                System.out.println(Thread.currentThread().getName()+"执行任务");
            },5,3, TimeUnit.SECONDS);
        }
//        scheduledExecutorService.shutdown();
    }
}

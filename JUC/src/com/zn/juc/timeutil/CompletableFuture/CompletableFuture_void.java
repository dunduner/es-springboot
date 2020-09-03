package com.zn.juc.timeutil.CompletableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class CompletableFuture_void {
    public static void main(String[] args) throws Exception {
        // 如果使用Runnable接口对象，那么泛型的类型必须为Void，表示没有返回值
        // 此线程执行完毕后开火
        CompletableFuture<Void> future =
                CompletableFuture.runAsync(() -> {
                    System.out.println("【FUTURE】将军正在温柔乡里美梦呢，他5秒钟后即将达到战场！");
                    try {
                        TimeUnit.SECONDS.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("【FUTURE】睡醒了，开始干活了。");
                });
        for (int x = 1; x <=5; x++) {
            new Thread(() -> {
                System.out.println("BEFORE【" + Thread.currentThread().getName() + "】进入炮兵阵地，等待命令，准备开火。");
                try {
                    future.get();//等待命令
                    System.out.println("AFTER【" + Thread.currentThread().getName() + "】接收到命令，立刻开火，干死那个死胖子。。" );
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, "炮兵-" + x).start();
        }
    }
}

package com.zn.juc.timeutil.CompletableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class CompletableFutureTest {
    public static void main(String[] args) throws InterruptedException {
        // 是直接利用构造方法来进行类的对象创建
        CompletableFuture<String> future = new CompletableFuture<String>();
        //模拟五个士兵
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                try {
                    System.out.println("【" + Thread.currentThread().getName() + "】进入炮兵阵地，等待命令，准备开火。");
                    String mingling = future.get();
                    if ("fire".equalsIgnoreCase(mingling)) {
                        System.out.println(Thread.currentThread().getName() + ",执行开火的命令！");
                    }
                    if ("clean".equalsIgnoreCase(mingling)) {
                        System.out.println(Thread.currentThread().getName() + ",回家吧,进攻取消了,回家睡觉！");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }, "忠诚的士兵" + i).start();
        }//for end
        TimeUnit.SECONDS.sleep(5);
        future.complete("clean");//发出clean等命令
    }
}

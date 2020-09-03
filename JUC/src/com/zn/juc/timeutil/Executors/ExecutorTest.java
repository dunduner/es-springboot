package com.zn.juc.timeutil.Executors;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
//            executorService.execute(() -> {
//                System.out.println(Thread.currentThread().getName()+"执行任务!");
//            });


            Future<?> submit = executorService.submit(() -> {
                System.out.println(Thread.currentThread().getName() + "执行任务!");
            });
            Object o = submit.get();
            System.out.println(o);
//            Future<?> submit = executorService.submit(() -> {
//                System.out.println(Thread.currentThread().getName() + "执行任务!");
//                return 1;
//            });
            System.out.println(submit.get());

        }

        executorService.shutdown();
    }
}

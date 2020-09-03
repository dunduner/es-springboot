package com.zn.juc.timeutil.Executors;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorCompletionServiceTest {
    public static void main(String[] args) throws Exception {
        // 现在创建了一个线程池
        ExecutorService service = Executors.newCachedThreadPool();
        CompletionService<String> completions = new ExecutorCompletionService<String>(service);
        for (int x = 0; x < 10; x++) {
            int temp = x;
            completions.submit(() -> {
                return Thread.currentThread()
                        .getName() + " - x = " + temp;
            });
        }
        for (int x = 0; x < 10; x++) {
            System.out.println(completions.take().get());
        }
        service.shutdown();
    }
}

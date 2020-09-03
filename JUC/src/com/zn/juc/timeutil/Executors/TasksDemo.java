package com.zn.juc.timeutil.Executors;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;

public class TasksDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Set<Callable<String>> tasks  =  new HashSet<Callable<String>>() ;
        for (int i = 0; i <10 ; i++) {
            int temp = i ;
            tasks.add(() -> {
                return Thread.currentThread().getName()  + "执行操作。 x = " + temp ;

            });
        }
        // 现在创建了一个线程池
        ExecutorService service = Executors.newCachedThreadPool();
        // 执行任务
//        String invokeAny = service.invokeAny(tasks);
//        System.out.println("返回结果：" + invokeAny);
        List<Future<String>> futures = service.invokeAll(tasks);
        for (Future<String> future : futures) {
            System.out.println(future.get());
        }
        service.shutdown();
    }
}

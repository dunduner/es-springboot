package com.zn.juc.timeutil.exchanger;
import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;




public class ExchangTest {
    public static void main(String[] args) {
        Exchanger<String> exc = new Exchanger<String>();
        //定义消费者
        for (int i = 0; i <3; i++) {
            new Thread(() -> {
                while (true){
                    try {
                        String data = exc.exchange(null);
                        TimeUnit.SECONDS.sleep(2);
                        if (data != null) {
                            System.out.println(Thread.currentThread().getName() + ",消费了数据：" + data);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, "消费者-" + i).start();
        }
        //定义2个生产者--循环2次 共生产4个数据
        for (int i = 0; i <2; i++) {
            int temp  = i;
            new Thread(() -> {
                for (int j = 0; j < 2; j++) {//共生产4个数据
                    String data = temp  + "-" + j;
                    try {
                        //让生产者放慢节奏
                        TimeUnit.SECONDS.sleep(2);
                        exc.exchange(data);
                        System.out.println("【" + Thread.currentThread().getName() + "】生产了数据：" + data);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }, "生产者NO：" + i).start();
        }

    }
}

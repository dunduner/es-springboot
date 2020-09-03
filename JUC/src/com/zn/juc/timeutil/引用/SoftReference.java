package com.zn.juc.timeutil.引用;

import java.util.concurrent.TimeUnit;

/**
 * @author zhangning
 * @date 2020/9/2
 */
public class SoftReference {

    public static void main(String[] args) {

        java.lang.ref.SoftReference<byte[]> m = new java.lang.ref.SoftReference<>(new byte[1024 * 1024 * 10]);
        System.out.println(m.get());
        System.gc();


        try {
            TimeUnit.SECONDS.sleep(1);
        }catch (Exception e0){
            e0.printStackTrace();
        }
        System.out.println(m.get());

        //-Xms=20M -Xmx=20M
        byte[] m2 = new byte[1024 * 1024 * 15];
        System.out.println("内存不够用了:"+m.get());
    }
}

package com.zn.juc.timeutil.CAS;

import com.zn.juc.timeutil.CAS.entity.Teacher;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

public class ABA_AtomicStampedReference_object {
    public static Teacher teacherOld  =new Teacher("张三",18,false);
    //1是维护的对象，0就是维护的版本
    private static AtomicStampedReference<Teacher> atomicStampedRef_teacher =
            new AtomicStampedReference<Teacher>(teacherOld, 0);

    public static void main(String[] args) {
        new Thread(() -> {
            Teacher newT = new Teacher("张三", 18, true);//改为上课
            int stamp = atomicStampedRef_teacher.getStamp();////获取当前标识别
            try {
                TimeUnit.SECONDS.sleep(1);//等待1秒 ，以便让干扰线程执行
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean boo = atomicStampedRef_teacher.compareAndSet(teacherOld, newT, stamp, stamp + 1);
            System.out.println("主线程是否cas成功："+boo);
            System.out.println(atomicStampedRef_teacher.getReference());
        },"主线程-").start();


        new Thread(() -> {
            Teacher newT = new Teacher("张三", 18, true);//改为上课
            boolean boo1 = atomicStampedRef_teacher.compareAndSet(teacherOld, newT, atomicStampedRef_teacher.getStamp(), atomicStampedRef_teacher.getStamp() + 1);
            boolean boo2 = atomicStampedRef_teacher.compareAndSet(newT, teacherOld, atomicStampedRef_teacher.getStamp(), atomicStampedRef_teacher.getStamp() + 1);
            System.out.println("干扰线程："+boo1+"|||||"+boo2);
        },"干扰线程-").start();
    }
}

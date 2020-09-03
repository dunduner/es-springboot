package com.zn.juc.timeutil;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReferenceArray;

public class TimeUtilTest {

    public static void main(String[] args) throws InterruptedException {
//        AtomicReference<Stu> atomicStu = new AtomicReference<Stu>();
//        Stu zz = new Stu(new AtomicLong(18l), "zz");
//        Stu ss = new Stu(new AtomicLong(22l), "ss");
//        atomicStu.set(zz);
//        boolean b = atomicStu.compareAndSet(zz, ss);
//        System.out.println(b);
//        System.out.println(atomicStu);
        AtomicInteger atomicInteger = new AtomicInteger(100);
        int i = atomicInteger.addAndGet(10);
        int i1 = atomicInteger.getAndAdd(-60);//当前返回值为get的值，但是此时的值已经为add后的值了
        System.out.println(i1);
        System.out.println(atomicInteger.get());
//        atomicInteger.compareAndSet(1,20);
//        System.out.println(atomicInteger);

        Stu zz = new Stu(new AtomicLong(18), "zz");
        Stu ss = new Stu(new AtomicLong(22), "ss");
        Stu[] stuArray = new Stu[]{zz, ss};
        AtomicReferenceArray atomicReferenceArray = new AtomicReferenceArray(stuArray);
        Stu o = (Stu) atomicReferenceArray.get(1);
        System.out.println(o);
    }
}

class Stu {
    private AtomicLong age;
    private String addr;

    public Stu(AtomicLong age, String addr) {
        this.addr = addr;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Stu{" +
                "age=" + age +
                ", addr='" + addr + '\'' +
                '}';
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public AtomicLong getAge() {
        return age;
    }

    public void setAge(AtomicLong age) {
        this.age = age;
    }
}

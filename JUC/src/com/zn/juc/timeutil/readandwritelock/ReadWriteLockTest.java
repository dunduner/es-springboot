package com.zn.juc.timeutil.readandwritelock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author zhangning
 * @date 2020/8/12
 */
public class ReadWriteLockTest {

    static Lock reentrantLock = new ReentrantLock();

    private  static  int value;

    static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    static  Lock readLocklock =  readWriteLock.readLock();
    static  Lock writeLock =  readWriteLock.writeLock();

    static void read(Lock lock){
        lock.lock();
        try {
            Thread.sleep(1000);
            System.out.println("read over!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    static void write(Lock lock){
        lock.lock();
        try {
            Thread.sleep(1000);
            System.out.println("write over!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 18; i++) {
            new Thread(() -> {
//                read(reentrantLock);
                read(readLocklock);
            },"读").start();
        }

        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
//                write(reentrantLock);
                write(writeLock);
            },"写").start();
        }

    }



}

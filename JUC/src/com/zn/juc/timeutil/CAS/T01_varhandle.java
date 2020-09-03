package com.zn.juc.timeutil.CAS;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;

/**
 * @author zhangning
 * @date 2020/8/13
 */
public class T01_varhandle {
    int x = 8;
    private static VarHandle handle;

//    static {
//        try {
//            handle = MethodHandles.lookup().findVarHandle(T01_varhandle.class, "x", int.class);
//        } catch (NoSuchFieldException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
//    }

    public static void main(String[] args) {
        T01_varhandle t01_varhandle = new T01_varhandle();
        try {
            //指向了x的引用
            handle = MethodHandles.lookup().findVarHandle(T01_varhandle.class, "x", int.class);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        int o = (int)handle.get(t01_varhandle);
        System.out.println(o);
        handle.set(t01_varhandle,20);
        int onew = (int)handle.get(t01_varhandle);
        System.out.println(onew);

        boolean b = handle.compareAndSet(t01_varhandle,onew, 100);
        if(b){
            o = (int)handle.get(t01_varhandle);
            System.out.println(o);
        }
       handle.getAndAdd(t01_varhandle, 99);

        System.out.println(t01_varhandle.x);

    }
}

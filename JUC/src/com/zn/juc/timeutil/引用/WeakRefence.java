package com.zn.juc.timeutil.引用;

import java.lang.ref.WeakReference;

/**
 * @author zhangning
 * @date 2020/9/2
 * 弱引用,当遭遇GC就被回收
 * 一般都是容器使用
 */
public class WeakRefence {
    public static void main(String[] args) {
        WeakReference<M> mWeakReference = new WeakReference<>(new M());
        System.out.println(mWeakReference.get());
        System.gc();
        System.out.println(mWeakReference.get());

        ThreadLocal threadLocal = new ThreadLocal();
        ThreadLocal threadLocal2 = new ThreadLocal();

        threadLocal.set("nihao");
        threadLocal.set("buhao");
        threadLocal2.set("2");
        System.out.println(threadLocal.get());
        System.out.println(threadLocal2.get());
    }

}

class M {
}

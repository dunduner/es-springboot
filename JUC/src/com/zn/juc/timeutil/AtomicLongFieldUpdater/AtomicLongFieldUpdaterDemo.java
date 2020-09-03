package com.zn.juc.timeutil.AtomicLongFieldUpdater;

import java.util.concurrent.atomic.AtomicLongFieldUpdater;

public class AtomicLongFieldUpdaterDemo {
    public static void main(String[] args) {
        Book javaObj = new Book(100, "java哈");
        javaObj.setBid(200);
        System.out.println(javaObj);
    }
}

class Book {
    private volatile long bid;    // 必须追加volatile关键字
    private String title;

    public Book(long bid, String title) {
        this.bid = bid;
        this.title = title;
    }

    public long getBid() {
        return bid;
    }

    public void setBid(long bid) {
        AtomicLongFieldUpdater bidFieldUpdater = AtomicLongFieldUpdater.newUpdater(Book.super.getClass(),"bid");
        bidFieldUpdater.compareAndSet(this, this.bid, bid);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    @Override
    public String toString() {
        return "图书编号：" + this.bid + ",名称：" + this.title;
    }
}

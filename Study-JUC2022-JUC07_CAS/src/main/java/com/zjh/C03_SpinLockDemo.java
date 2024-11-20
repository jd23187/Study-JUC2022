package com.zjh;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author: JingDe
 * @date: 2024/9/11 17:06
 * @description:
 * @params:
 * @return:
 */
/*
 * 题目：实现一个自旋锁，复习CAS思想
 * 自旋锁好处：循环比较获取没有类似wait的阻塞。
 *
 * 通过CAS操作完成自旋锁，A线程先进来调用myLock方法自己持有锁5秒钟，B随后进来后发现
 * 当前有线程持有锁，所以只能通过自旋等待，直到A释放锁后B随后抢到。
 * */
public class C03_SpinLockDemo {
    AtomicReference<Thread> atomicReference = new AtomicReference<Thread>();

    public void lock() {
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + "\t'--come in");
        while (!atomicReference.compareAndSet(null, thread)) {

        }
    }

    public void unlock() {
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread, null);
        System.out.println(Thread.currentThread().getName() + "\t'--task over, unlock...");
    }

    public static void main(String[] args) {
        C03_SpinLockDemo spinLockDemo = new C03_SpinLockDemo();
        new Thread(()->{
            spinLockDemo.lock();
            try {TimeUnit.MILLISECONDS.sleep(5000);} catch (InterruptedException e) { e.printStackTrace();}
            spinLockDemo.unlock();
        },"A").start();

        try {TimeUnit.MILLISECONDS.sleep(500);} catch (InterruptedException e) { e.printStackTrace();}

        new Thread(()->{
            spinLockDemo.lock();
            spinLockDemo.unlock();
        },"B").start();
    }
}

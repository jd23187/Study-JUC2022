package com.zjh;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class MyNumber {
    AtomicInteger atomicInteger = new AtomicInteger();

    public void addPlusPlus() {
        atomicInteger.getAndIncrement();
    }
}

public class C01_AtomicIntegerDemo {
    public static final int SIZE = 50;

    public static void main(String[] args) throws InterruptedException {
        MyNumber myNumber = new MyNumber();
        CountDownLatch countDownLatch = new CountDownLatch(SIZE);

        for (int i = 0; i < SIZE; i++) {
            new Thread(()->{
                try {
                    for (int j = 0; j < 1000; j++) {
                        myNumber.addPlusPlus();
                    }
                }finally {
                    countDownLatch.countDown();
                }
            },(i+1)+"").start();
        }
        // 等待上面50个线程全部计算完成后，再去获得最终值---时间有长有短，不能这样写
        // try {TimeUnit.MILLISECONDS.sleep(20);} catch (InterruptedException e) { e.printStackTrace();}
        countDownLatch.await();

        System.out.println(Thread.currentThread().getName()+"\t result: "+ myNumber.atomicInteger.get());
    }
}

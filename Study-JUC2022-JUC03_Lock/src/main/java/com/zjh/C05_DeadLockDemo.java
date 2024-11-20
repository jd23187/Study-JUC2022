package com.zjh;

import java.util.concurrent.TimeUnit;

/**
 * @author: JingDe
 * @date: 2024/9/10 11:30
 * @description:
 * @params:
 * @return:
 */
public class C05_DeadLockDemo {
    public static void main(String[] args) {
        final Object objectA = new Object();
        final Object objectB = new Object();

        new Thread(()->{
            synchronized (objectA){
                System.out.println(Thread.currentThread().getName()+"\t 自己持有A锁，希望得到B锁");
                try {TimeUnit.MILLISECONDS.sleep(20);} catch (InterruptedException e) { e.printStackTrace();}
                synchronized (objectB){
                    System.out.println(Thread.currentThread().getName()+"\t 成功获得B锁");
                }
            }
        },"A").start();

        new Thread(()->{
            synchronized (objectB){
                System.out.println(Thread.currentThread().getName()+"\t 自己持有B锁，希望得到A锁");
                try {TimeUnit.MILLISECONDS.sleep(20);} catch (InterruptedException e) { e.printStackTrace();}
                synchronized (objectA){
                    System.out.println(Thread.currentThread().getName()+"\t 成功获得A");
                }
            }
        },"B").start();
    }
}

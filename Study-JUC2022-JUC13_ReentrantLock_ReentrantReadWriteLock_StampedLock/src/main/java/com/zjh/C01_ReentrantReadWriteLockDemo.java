package com.zjh;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author: JingDe
 * @date: 2024/11/30 22:11
 * @description:
 * @params:
 * @return:
 */

//资源类，模拟一个简单的缓存
class MyResource{
    Map<String,String> map = new HashMap<String,String>();
    //=====ReentrantLockk等价于=====synchronized，之前讲解过
    Lock lock = new ReentrantLock();
    //=====ReentrantReadwriteLockk一体两面，读写互斤，读读共享
    ReadWriteLock rwLock = new ReentrantReadWriteLock();
    // public void write(String key,String value){
    //     lock.lock();
    //     try {
    //         System.out.println(Thread.currentThread().getName()+"\t正在写入");
    //         map.put(key,value);
    //         try {TimeUnit.MILLISECONDS.sleep(500);} catch (InterruptedException e) { e.printStackTrace();}
    //         System.out.println(Thread.currentThread().getName()+"\t完成写入");
    //     }finally {
    //         lock.unlock();
    //     }
    // }
    // public void read(String key){
    //     lock.lock();
    //     try {
    //         System.out.println(Thread.currentThread().getName()+"\t正在读取");
    //         String result = map.get(key);
    //         try {TimeUnit.MILLISECONDS.sleep(200);} catch (InterruptedException e) { e.printStackTrace();}
    //         System.out.println(Thread.currentThread().getName()+"\t完成读取\t"+result);
    //     }finally {
    //         lock.unlock();
    //     }
    // }

    public void write(String key,String value){
        rwLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t正在写入");
            map.put(key,value);
            try {TimeUnit.MILLISECONDS.sleep(500);} catch (InterruptedException e) { e.printStackTrace();}
            System.out.println(Thread.currentThread().getName()+"\t完成写入");
        }finally {
            rwLock.writeLock().unlock();
        }
    }
    public void read(String key){
        rwLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t正在读取");
            String result = map.get(key);
            // try {TimeUnit.MILLISECONDS.sleep(200);} catch (InterruptedException e) { e.printStackTrace();}
            //暂停2000毫秒，演示读锁没有完成之前，写锁无法获得
            try {TimeUnit.MILLISECONDS.sleep(2000);} catch (InterruptedException e) { e.printStackTrace();}
            System.out.println(Thread.currentThread().getName()+"\t完成读取\t"+result);
        }finally {
            rwLock.readLock().unlock();
        }
    }
}
public class C01_ReentrantReadWriteLockDemo {
    public static void main(String[] args) {
        MyResource myResource = new MyResource();
        for (int i = 1; i <= 10; i++) {
            int finalI = i;
            new Thread(()->{
                myResource.write(finalI +"", finalI +"");
            },String.valueOf(i)).start();
        }
        for (int i = 1; i <= 10; i++) {
            int finalI = i;
            new Thread(()->{
                myResource.read(finalI +"");
            },String.valueOf(i)).start();
        }

        try {TimeUnit.MILLISECONDS.sleep(1000);} catch (InterruptedException e) { e.printStackTrace();}

        for (int i = 1; i <= 3; i++) {
            int finalI = i;
            new Thread(()->{
                myResource.write(finalI +"", finalI +"");
            },"新的写锁线程-》"+String.valueOf(i)).start();
        }
    }
}







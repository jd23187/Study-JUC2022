package com.zjh;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author: JingDe
 * @date: 2024/12/1 22:52
 * @description: 锁降级：遵循获取写锁>再获取读锁>再释放写锁的次序，写锁能够降级成为读锁。
 * <p>
 * 如果一个线程占有了写锁，在不释放写锁的情况下，它还能占有读锁，即写锁降级为读锁。
 * <p>
 * 读没有完成时候写锁无法获得锁，必须要等着读锁读完后才有机会写
 * @params:
 * @return:
 */
public class C02_LockDownGradingDemo {
    public static void main(String[] args) {
        ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();
        ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();


        //正常AB两个线程
        // //A
        // writeLock.lock();
        // System.out.println("----写入");
        // writeLock.unlock();
        // //B
        // readLock.lock();
        // System.out.println("读取");
        // readLock.unlock();


        // //本例，only one同一个线程
        // writeLock.lock();//
        // System.out.println("----写入");
        //
        // /**
        //  * biz
        //  */
        // readLock.lock();//获得写锁与释放写锁之间获得了一个读锁，
        // System.out.println("读取");
        // /**
        //  *biz
        //  */
        // writeLock.unlock();//
        //
        // readLock.unlock();


        //反过来不行
        //本例，only one同一个线程
        readLock.lock();
        System.out.println("读取");

        writeLock.lock();//
        System.out.println("----写入");

        /**
         * biz
         */

        writeLock.unlock();//
        readLock.unlock();
    }


}

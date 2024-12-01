package com.zjh;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: JingDe
 * @date: 2024/10/27 22:49
 * @description:
 * @params:
 * @return:
 */
public class C01_AQSDemo {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock(true);

        lock.lock();
        try {

        }finally {
            lock.unlock();
        }

        // new CountDownLatch(10);
        // new Semaphore();
    }
}

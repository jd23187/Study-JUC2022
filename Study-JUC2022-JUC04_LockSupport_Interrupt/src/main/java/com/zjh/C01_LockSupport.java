package com.zjh;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author: JingDe
 * @date: 2024/9/10 13:51
 * @description:
 * @params:
 * @return:
 */

/*
* 6. volatile

  - 易变的
    - volatile：易变的，不定性的，可以修饰成员变量
      - 表示该成员变量的值是易变的，<u>每一次获取它的值都要从主存中重新读取，以保证在多线程中，不同线程读取到的该值都是最新的</u>
      - 因为Java中多线程读取某个成员变量时，发现一段时间内它的值都未发生变化，Java执行引擎就会把这个值放在缓存中，以后的线程读取，就会读取这个缓存值，即使这个时候某个线程修改了该变量主存中的值，Java执行引擎仍然会去读取缓存的值，而如果希望线程总是读取最新的该变量的值，那么可以在变量前面加volatile，使得<u>Java执行引擎都从主存中读取，而不缓存</u>
  * */
public class C01_LockSupport {
    static volatile boolean isStop = false;
    static AtomicBoolean atomicBoolean = new AtomicBoolean(false);

    public static void main(String[] args) {
        // m1_volatile();
        // m2_atomicBollean();
        m3_interruptAPI();
    }

    private static void m3_interruptAPI() {
        Thread t1 = new Thread(()->{
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println(Thread.currentThread().getName() + "\t isInterrupted()被修改为true，程序停止");
                    break;
                }
                System.out.println("t1 ------hello interrupt api");
            }
        },"t1");
        t1.start();

        System.out.println("-----ti的默认中断标志位："+t1.isInterrupted());

        try {TimeUnit.MILLISECONDS.sleep(20);} catch (InterruptedException e) { e.printStackTrace();}
        //t2向t1发出协商，将t1的中断标志位设为rue希望t1停下来
        new Thread(() -> {
            t1.interrupt();
        }, "t2").start();
    }

    private static void m2_atomicBollean() {
        new Thread(() -> {
            while (true) {
                if (atomicBoolean.get()) {
                    System.out.println(Thread.currentThread().getName() + "\t atomicBoolean被修改为true，程序停止");
                    break;
                }
                System.out.println("t1 ------hello atomicBoolean");
            }
        }, "t1").start();

        try {
            TimeUnit.MILLISECONDS.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> {
            atomicBoolean.set(true);
        }, "t2").start();
    }

    private static void m1_volatile() {
        new Thread(() -> {
            while (true) {
                if (isStop) {
                    System.out.println(Thread.currentThread().getName() + "\t isStrp被修改为true，程序停止");
                    break;
                }
                System.out.println("t1 ------hello volatitle");
            }
        }, "t1").start();

        try {
            TimeUnit.MILLISECONDS.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> {
            isStop = true;
        }, "t2").start();
    }
}

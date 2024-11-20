package com.zjh;

/**
 * @author: JingDe
 * @date: 2024/10/27 21:54
 * @description: 锁粗化
 * 假如方法中首尾相接，前后相邻的都是同一个锁对象，那JIT编译器就会把这几个synchronized块合并成一个大块，
 * 加粗加大范围，一次申请锁使用即可，避免次次的申请和释放锁，提升了性能
 * @params:
 * @return:
 */
public class C07_LockBigDemo {
    static Object objectLock = new Object();

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (objectLock) {
                System.out.println("1111111111111111");
            }
            synchronized (objectLock) {
                System.out.println("22222222222222222");
            }
            synchronized (objectLock) {
                System.out.println("333333333333333");
            }
            synchronized (objectLock) {
                System.out.println("444444444444444");
            }

            synchronized (objectLock) {
                System.out.println("1111111111111111");
                System.out.println("22222222222222222");
                System.out.println("333333333333333");
                System.out.println("444444444444444");
            }
        }, "t1").start();
    }
}

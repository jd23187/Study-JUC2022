package com.zjh;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @author: JingDe
 * @date: 2024/9/27 23:59
 * @description: 以一种线程安全的方式操作非线程安全对象的某些字段。
 * <p>
 * 需求：
 * 10个线程，
 * 每个线程转账1000，
 * 不使用synchronized，尝试使用AtomicIntegerFieldUpdater来实现。
 * @params:
 * @return:
 */
// class BankAccount {
//     String bankName = "ICBC";
//     public int money = 0; //线程
//
//     public synchronized void add() {
//         money++;
//     }
// }
//
// public class C04_AtomicIntegerFieldUpdaterDemo {
//     public static void main(String[] args) throws InterruptedException {
//         BankAccount bankAccount = new BankAccount();
//         CountDownLatch countDownLatch = new CountDownLatch(10);//十个线程
//
//         for (int i = 1; i <= 10; i++) {
//             new Thread(() -> {
//                 try {
//                     for (int j = 1; j <= 1000; j++) {
//                         bankAccount.add();
//                     }
//                 }finally {
//                     countDownLatch.countDown();
//                 }
//             },String.valueOf(i)).start();
//         }
//         countDownLatch.await();
//
//         System.out.println(Thread.currentThread().getName() + "\tresult: " +bankAccount.money);
//     }
// }


class BankAccount {
    String bankName = "ICBC";
    // 更新的对象 <u>属性</u> 必须使用 <u>public volatile 修饰符</u>
    public volatile int money = 0; //线程

    public void add() {
        money++;
    }
    // 因为对象的属性修改类型原子类都是抽象类，所以每次使用都必须使用静态方法 newUpdater()创建一个更新器，并且需要设置想要更新的类和属性

    AtomicIntegerFieldUpdater<BankAccount> fieldUpdater = AtomicIntegerFieldUpdater.newUpdater(BankAccount.class, "money");

    //不加synchronized，保证高性能原子性，局部微创小手术
    public void transMoney(BankAccount bankAccount){
        fieldUpdater.getAndIncrement(bankAccount);
    }
}

public class C04_AtomicIntegerFieldUpdaterDemo {
    public static void main(String[] args) throws InterruptedException {
        BankAccount bankAccount = new BankAccount();
        CountDownLatch countDownLatch = new CountDownLatch(10);//十个线程

        for (int i = 1; i <= 10; i++) {
            new Thread(() -> {
                try {
                    for (int j = 1; j <= 1000; j++) {
                        // bankAccount.add();
                        bankAccount.transMoney(bankAccount);
                    }
                }finally {
                    countDownLatch.countDown();
                }
            },String.valueOf(i)).start();
        }
        countDownLatch.await();

        System.out.println(Thread.currentThread().getName() + "\tresult: " +bankAccount.money);
    }
}

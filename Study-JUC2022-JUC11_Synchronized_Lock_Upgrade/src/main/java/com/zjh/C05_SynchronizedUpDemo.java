package com.zjh;

import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.TimeUnit;

/**
 * @author: JingDe
 * @date: 2024/10/27 21:26
 * @description:
 * @params:
 * @return:
 */
public class C05_SynchronizedUpDemo {
    // public static void main(String[] args) {
    //     //先睡眠5秒，保证开启偏向锁
    //     try {TimeUnit.MILLISECONDS.sleep(5000);} catch (InterruptedException e) { e.printStackTrace();}
    //     Object o = new Object();
    //     System.out.println("本应是偏向锁");
    //     System.out.println(ClassLayout.parseInstance(o).toPrintable());
    //
    //     o.hashCode();//没有重写，一致性哈希，重写后无效当一个对象已经计算过identity hash code，它就无法进入偏向锁状态；
    //
    //     synchronized (o){
    //         System.out.println("本应是偏回锁，但是由于计算过一致性哈希，会直接升级为轻量级锁");
    //         System.out.println(ClassLayout.parseInstance(o).toPrintable());
    //     }
    // }

    public static void main(String[] args) {
        //先睡眠5秒，保证开启偏向锁
        try {TimeUnit.MILLISECONDS.sleep(5000);} catch (InterruptedException e) { e.printStackTrace();}
        Object o = new Object();

        synchronized (o){
            o.hashCode();//没有重写，一致性哈希，重写后无效
            System.out.println("偏向锁过程中遇到一致性哈希计算请求，立马撤销偏向模式，膨胀为重量级锁");
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }
    }
}

package com.zjh;

import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.TimeUnit;

/**
 * @author: JingDe
 * @date: 2024/10/14 22:58
 * @description:
 * @params:
 * @return:
 */
public class C03_SynchronizedUpDemo {
    // public static void main(String[] args) {
    //     try {TimeUnit.MILLISECONDS.sleep(5000);} catch (InterruptedException e) { e.printStackTrace();}
    //
    //     Object o = new Object();
    //     synchronized (o){
    //         System.out.println(ClassLayout.parseInstance(o).toPrintable());
    //     }
    // }
    public static void main(String[] args) {
        try {TimeUnit.MILLISECONDS.sleep(5000);} catch (InterruptedException e) { e.printStackTrace();}

        Object o = new Object();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());

        System.out.println("===================");
        new Thread(()->{
            synchronized (o){
                System.out.println(ClassLayout.parseInstance(o).toPrintable());
            }
        },"t1").start();
    }
    private static void noLock(){
    }

}

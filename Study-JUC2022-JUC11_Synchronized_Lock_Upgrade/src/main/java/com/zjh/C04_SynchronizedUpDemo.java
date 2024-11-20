package com.zjh;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author: JingDe
 * @date: 2024/10/22 20:50
 * @description:
 * @params:
 * @return:
 */
public class C04_SynchronizedUpDemo {
    public static void main(String[] args) {
        Object o = new Object();

        new Thread(()->{
            synchronized (o){
                System.out.println(ClassLayout.parseInstance(o).toPrintable());
            }
        },"t1").start();
    }
}

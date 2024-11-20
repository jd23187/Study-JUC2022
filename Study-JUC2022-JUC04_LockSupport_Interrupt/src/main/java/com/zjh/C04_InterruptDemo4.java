package com.zjh;

/**
 * @author: JingDe
 * @date: 2024/9/10 15:47
 * @description:
 * @params:
 * @return:
 */
public class C04_InterruptDemo4 {
    public static void main(String[] args) {
        //测试当前线程是否被中断（检查中断标志），返回一个booLean并清除中断状态
        //第二次再调用时中断状态已经被清除，将返回一个faLse。

        System.out.println(Thread.currentThread().getName() + "\t" + Thread.interrupted());
        System.out.println(Thread.currentThread().getName() + "\t" + Thread.interrupted());
        System.out.println("---1");
        Thread.currentThread().interrupt();//中断标志位设置为true
        System.out.println("----2");
        System.out.println(Thread.currentThread().getName()+"\t"+Thread.interrupted());//1、返回当前线程的中断状态，测试当前线程是否已被中断 2、将当前线程的中断状态清零并重新设为false，清除线程的中断状态
        System.out.println(Thread.currentThread().getName()+"\t"+Thread.interrupted());

        Thread.interrupted(); //静态方法
        Thread.currentThread().isInterrupted();//实例方法
    }
}

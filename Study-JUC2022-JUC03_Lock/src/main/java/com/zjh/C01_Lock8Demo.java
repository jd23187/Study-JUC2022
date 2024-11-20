package com.zjh;

import java.util.concurrent.TimeUnit;

/**
 * @author: JingDe
 * @date: 2024/9/7 15:21
 * @description:
 * @params:
 * @return:
 */
//资源类
class Phone {
    // public synchronized void sendEmail() {
    //     try {TimeUnit.SECONDS.sleep(3);} catch (InterruptedException e) { e.printStackTrace();}//case2
    //     System.out.println(".........sendEmail");
    // }
    //
    // public synchronized void sendSMS() {
    //     System.out.println(".........sendSMS");
    // }
    public static synchronized void sendEmail() {
        // synchronized (o){
        //
        // }
        try {TimeUnit.SECONDS.sleep(3);} catch (InterruptedException e) { e.printStackTrace();}//case2
        System.out.println(".........sendEmail");
    }

    // public static synchronized void sendSMS() {
    //     System.out.println(".........sendSMS");
    // }
    public synchronized void sendSMS() {
        System.out.println(".........sendSMS");
    }
    public void hello(){
        System.out.println("-----hello");
    }
}

/*
 * 题目：谈谈你对多线程锁的理解，8锁案例说明
 * 口诀：线程  操作  资源类,
 *
 * 8锁案例说明：
 * 1、标准访问有ab两个线程，请问先打印邮件还是短信            Email
 * 2、sendEmail方法中加入暂停3秒钟，请问先打印邮件还是短信    Email
 * 3、添加一个普通的heLLo方法，请问先打印邮件还是heLLo       hello
 * 4、有两部手机，请问先打印邮件还是短信                    SMS
 * 5、有两个静态同步方法，有1部手机，请问先打印邮件还是短信     Email
 * 6、有两个静态同步方法，有2部手机，请问先打印邮件还是短信     Email
 * 7、有1个静态同步方法，有1个普通同步方法，有1部手机，请问先打印邮件还是短信      SMS
 * 8、有1个静态同步方法，有1个普通同步方法，有2部手机，请问先打印邮件还是短信      SMS
 * */
public class C01_Lock8Demo {
    public static void main(String[] args) {
        // case1();
        // case2();
        // case3();
        // case4();
        // case5();
        // case6();
        // case7();
        case8();
    }

    private static void case8() {
        Phone phone1 = new Phone();
        Phone phone2 = new Phone();

        new Thread(() -> {
            phone1.sendEmail();
        }, "a").start();

        //暂停毫秒，保证a线程先启动
        try {TimeUnit.MILLISECONDS.sleep(200);} catch (InterruptedException e) { e.printStackTrace();}
        new Thread(() -> {
            phone2.sendSMS();
            // phone1.hello();
        }, "b").start();
    }

    private static void case7() {
        Phone phone1 = new Phone();

        new Thread(() -> {
            phone1.sendEmail();
        }, "a").start();

        //暂停毫秒，保证a线程先启动
        try {TimeUnit.MILLISECONDS.sleep(200);} catch (InterruptedException e) { e.printStackTrace();}
        new Thread(() -> {
            phone1.sendSMS();
            // phone1.hello();
        }, "b").start();
    }

    private static void case6() {
        Phone phone1 = new Phone();
        Phone phone2= new Phone();

        new Thread(() -> {
            phone1.sendEmail();
        }, "a").start();

        //暂停毫秒，保证a线程先启动
        try {TimeUnit.MILLISECONDS.sleep(200);} catch (InterruptedException e) { e.printStackTrace();}
        new Thread(() -> {
            // phone1.sendSMS();
            // phone1.hello();
            phone2.sendSMS();
        }, "b").start();
    }

    private static void case5() {
        Phone phone = new Phone();

        new Thread(() -> {
            phone.sendEmail();
        }, "a").start();

        //暂停毫秒，保证a线程先启动
        try {TimeUnit.MILLISECONDS.sleep(200);} catch (InterruptedException e) { e.printStackTrace();}
        new Thread(() -> {
            // phone1.sendSMS();
            // phone1.hello();
            phone.sendSMS();
        }, "b").start();
    }

    private static void case4() {
        Phone phone1 = new Phone();
        Phone phone2 = new Phone();

        new Thread(() -> {
            phone1.sendEmail();
        }, "a").start();

        //暂停毫秒，保证a线程先启动
        try {TimeUnit.MILLISECONDS.sleep(200);} catch (InterruptedException e) { e.printStackTrace();}
        new Thread(() -> {
            // phone1.sendSMS();
            // phone1.hello();
            phone2.sendSMS();
        }, "b").start();
    }

    private static void case3() {
        Phone phone = new Phone();

        new Thread(() -> {
            phone.sendEmail();
        }, "a").start();

        //暂停毫秒，保证a线程先启动
        try {TimeUnit.MILLISECONDS.sleep(200);} catch (InterruptedException e) { e.printStackTrace();}
        new Thread(() -> {
            // phone.sendSMS();
            phone.hello();
        }, "b").start();
    }

    private static void case2() {
        Phone phone = new Phone();

        new Thread(() -> {
            phone.sendEmail();
        }, "a").start();

        //暂停毫秒，保证a线程先启动
        try {TimeUnit.MILLISECONDS.sleep(20);} catch (InterruptedException e) { e.printStackTrace();}
        new Thread(() -> {
            phone.sendSMS();
        }, "b").start();
    }

    private static void case1() {
        Phone phone = new Phone();

        new Thread(() -> {
            phone.sendEmail();
        }, "a").start();

        //暂停毫秒，保证a线程先启动
        try {TimeUnit.MILLISECONDS.sleep(20);} catch (InterruptedException e) { e.printStackTrace();}
        new Thread(() -> {
            phone.sendSMS();
        }, "b").start();
    }
}

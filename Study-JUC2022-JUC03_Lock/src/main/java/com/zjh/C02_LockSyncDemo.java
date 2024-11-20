package com.zjh;

/**
 * @author: JingDe
 * @date: 2024/9/7 16:43
 * @description:
 * @params:
 * @return:
 */
class Book{

}
public class C02_LockSyncDemo {
    Object o = new Object();
    Book b1 = new Book();

    public void m1() {
        synchronized (b1){
            System.out.println("----hello synchronized code block");
            throw new RuntimeException("----exp");
        }
    }

    public synchronized void m2() {
        System.out.println("----hello synchronized m2");
    }

    public static synchronized void m3() {
        System.out.println("----hello synchronized m2");
    }
}

package com.zjh;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: JingDe
 * @date: 2024/9/9 8:48
 * @description:
 * @params:
 * @return:
 */

//资源类，模拟3个售票员卖完50张票
class Ticket {
    private int number = 50;
    // ReentrantLock lock = new ReentrantLock();
    ReentrantLock lock = new ReentrantLock(true);
    public void sale(){
        lock.lock();
        try {
            if (number>0){
                System.out.println(Thread.currentThread().getName() + "卖出第：\t" + (number--) + "\t 还剩下：" + number);
            }
        }finally {
            lock.unlock();
        }
    }
}
public class C03_SaleTicketDemo {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        new Thread(()->{for(int i = 0; i <55; i++) ticket.sale();},"a").start();
        new Thread(()->{for(int i = 0; i <55; i++) ticket.sale();},"b").start();
        new Thread(()->{for(int i = 0; i <55; i++) ticket.sale();},"c").start();
    }
}

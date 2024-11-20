package com.zjh;

/**
 * @author: JingDe
 * @date: 2024/10/14 22:25
 * @description:
 * @params:
 * @return:
 */
class Ticket{
    private int number = 50;
    Object lockObject = new Object();
    public void sale(){
        synchronized (lockObject){
            if (number>0){
                System.out.println(Thread.currentThread().getName()+"卖出第：\t"+(number--)+"\t还剩："+number);
            }
        }
    }
}
public class C02_SaleTicketDemo {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        new Thread(()->{for (int i = 0; i <55; i++) {ticket.sale();}},"t1").start();
        new Thread(()->{for (int i = 0; i <55; i++) {ticket.sale();}},"t2").start();
        new Thread(()->{for (int i = 0; i <55; i++) {ticket.sale();}},"t3").start();
    }
}

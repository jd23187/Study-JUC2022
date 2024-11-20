package com.zjh;

/**
 * @author: JingDe
 * @date: 2024/10/8 16:42
 * @description:
 * @params:
 * @return:
 */
public class C01_ObjectHeadDemo {
    public static void main(String[] args) {
        Object o = new Object();//? new一个对象，占内存多少？

        System.out.println(o.hashCode());//这个hashcode记录在对象的什么地方？

        synchronized (o){

        }
        System.gc();//手动收集垃圾。......,15次可以从新生代---养老区

        Customer c1 = new Customer();
        Customer c2 = new Customer();
        Customer c3 = new Customer();
    }
}

//只有一个对象头的实例对象，16字节，忽略压缩指针的影响 +  实例变量，4字节 + 标志位，1字节 = 21字节--->对齐填充，24字节
class Customer{
    //1、第一种情况，只有对象头，没有其它任何实例数据

    //2、第二种情况，int + boolean，默认满足对其填充，24 bytes
    int id;
    boolean flag = false;
    // String customerName;
}

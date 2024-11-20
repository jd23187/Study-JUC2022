package com.zjh;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

/**
 * @author: JingDe
 * @date: 2024/10/8 17:40
 * @description:
 * @params:
 * @return:
 */
public class C02_JOLDemo {
    public static void main(String[] args) {
        // Thread.currentThread()
        //VM的详细情况
        // System.out.println(VM.current().details());
        //所有的对象分配的字节数都是8的整数倍
        // System.out.println(VM.current().objectAlignment());

        Object o = new Object();
        // System.out.println(ClassLayout.parseInstance(o).toPrintable());

        Customer customer = new Customer();
        System.out.println(ClassLayout.parseInstance(customer).toPrintable());
    }
}


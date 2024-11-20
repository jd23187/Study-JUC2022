package com.zjh;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author: JingDe
 * @date: 2024/10/8 19:57
 * @description:
 * @params:
 * @return:
 */
public class C01_SynchronizedupDemo {
    public static void main(String[] args) {
        Object o = new Object();

        System.out.println("10进制："+o.hashCode());
        System.out.println("16进制："+Integer.toHexString(o.hashCode()));
        System.out.println("2进制："+Integer.toBinaryString(o.hashCode()));

        //2进制：10101010000001110000110011101
        //      00010101 01000000 11100001 10011101
        //      00010101010000001110000110011101
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
    }
}

package com.zjh;

import java.util.HashMap;

/**
 * @author: JingDe
 * @date: 2024/10/8 15:22
 * @description:
 * @params:
 * @return:
 */
public class C04_T1 {
    volatile boolean flag;
    public static void main(String[] args) {
        ThreadLocal<String> tl = new ThreadLocal<>();  //lin1
        tl.set("1111@qq.com");                         //lin2
        tl.get();                                       //lin3

        new HashMap<>().put(null, 123);

    }
}

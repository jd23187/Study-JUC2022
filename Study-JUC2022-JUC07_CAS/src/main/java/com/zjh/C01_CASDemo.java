package com.zjh;

import java.util.concurrent.atomic.AtomicInteger;

public class C01_CASDemo {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);
        System.out.println(atomicInteger.compareAndSet(5, 2024) + "\t" + atomicInteger.get());
        System.out.println(atomicInteger.compareAndSet(5, 2025) + "\t" + atomicInteger.get());
    }
}

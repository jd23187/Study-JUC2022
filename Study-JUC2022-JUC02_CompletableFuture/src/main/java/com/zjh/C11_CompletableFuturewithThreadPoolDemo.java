package com.zjh;

import java.util.concurrent.*;

/**
 * @author: JingDe
 * @date: 2024/9/7 8:33
 * @description:
 * @params:
 * @return:
 */
public class C11_CompletableFuturewithThreadPoolDemo {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(3);

        CompletableFuture<Void> completableFuture = CompletableFuture.supplyAsync(() -> {
            //暂停几秒钟线程
            try {TimeUnit.MILLISECONDS.sleep(100);} catch (InterruptedException e) { e.printStackTrace();}
            System.out.println("task1" + "\t" + Thread.currentThread().getName());
            return "abc";
        }, threadPool).thenRun(() -> {
            //暂停几秒钟线程
            try {TimeUnit.MILLISECONDS.sleep(20);} catch (InterruptedException e) { e.printStackTrace();}
            System.out.println("task2" + "\t" + Thread.currentThread().getName());
        }).thenRun(() -> {
            //暂停几秒钟线程
            try {TimeUnit.MILLISECONDS.sleep(30);} catch (InterruptedException e) { e.printStackTrace();}
            System.out.println("task3" + "\t" + Thread.currentThread().getName());
        }).thenRun(() -> {
            //暂停几秒钟线程
            try {TimeUnit.MILLISECONDS.sleep(10);} catch (InterruptedException e) { e.printStackTrace();}
            System.out.println("task4" + "\t" + Thread.currentThread().getName());
        });
        try {System.out.println(completableFuture.get(2, TimeUnit.SECONDS));} catch (Exception e) {e.printStackTrace();} finally {threadPool.shutdown();}
    }
}

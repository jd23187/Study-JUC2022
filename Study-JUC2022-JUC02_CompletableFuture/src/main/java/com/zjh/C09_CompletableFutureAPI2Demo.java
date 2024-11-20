package com.zjh;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author: JingDe
 * @date: 2024/9/6 17:28
 * @description:
 * @params:
 * @return:
 */
public class C09_CompletableFutureAPI2Demo {
    public static void main(String[] args) {
        // thenApply();
        handle();
    }


    private static void thenApply() {
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        CompletableFuture.supplyAsync(() -> {
            //暂停几秒钟线程
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("step1");
            return 1;
        }, threadPool).thenApply(f -> {
            int i = 10 / 0;

            System.out.println("step2");
            return f + 2;
        }).thenApply(f -> {
            System.out.println("step3");
            return f + 3;
        }).whenComplete((v, e) -> {
            if (e == null) {
                System.out.println("----计算结果： " + v);
            }
        }).exceptionally(e -> {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return null;
        });

        System.out.println(Thread.currentThread().getName() + "---主线程先去跑其他任务");
        threadPool.shutdown();
        //主线程不要立刻结束，否则compLetabLeFuture默认使用的线程池会立刻关闭：
        // try {
        //     TimeUnit.SECONDS.sleep(2);
        // } catch (InterruptedException e) {
        //     e.printStackTrace();
        // }
    }

    private static void handle() {
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        CompletableFuture.supplyAsync(() -> {
            //暂停几秒钟线程
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("step1");
            return 1;
        }, threadPool).handle((f, e) -> {
            int i = 10 / 0;

            System.out.println("step2");
            return f + 2;
        }).handle((f, e) -> {
            System.out.println("step3");
            return f + 3;
        }).whenComplete((v, e) -> {
            if (e == null) {
                System.out.println("----计算结果： " + v);
            }
        }).exceptionally(e -> {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return null;
        });

        System.out.println(Thread.currentThread().getName() + "---主线程先去跑其他任务");
        threadPool.shutdown();
        //主线程不要立刻结束，否则compLetabLeFuture默认使用的线程池会立刻关闭：
        // try {
        //     TimeUnit.SECONDS.sleep(2);
        // } catch (InterruptedException e) {
        //     e.printStackTrace();
        // }
    }
}

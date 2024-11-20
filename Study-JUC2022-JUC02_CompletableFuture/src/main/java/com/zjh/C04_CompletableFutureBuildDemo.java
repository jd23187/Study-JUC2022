package com.zjh;

import java.util.concurrent.*;

/**
 * @author: JingDe
 * @date: 2024/9/6 15:04
 * @description:
 * @params:
 * @return:
 */
public class C04_CompletableFutureBuildDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // extracted1();
        // extracted2();

        extracted3();

    }

    //1、无返回值-无指定线程池
    private static void extracted1() throws InterruptedException, ExecutionException {
        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName());
            ///暂停几秒线程
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println(completableFuture.get());
    }

    //2、无返回值-指定线程池
    private static void extracted2() throws InterruptedException, ExecutionException {
        ExecutorService threadPool = Executors.newFixedThreadPool(3);

        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName());
            ///暂停几秒线程
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, threadPool);

        System.out.println(completableFuture.get());
        threadPool.shutdown();
    }

    //3、有返回值- 指定线程池
    private static void extracted3() throws InterruptedException, ExecutionException {
        ExecutorService threadPool = Executors.newFixedThreadPool(3);

        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName());
            ///暂停几秒线程
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello supplyAsync";
        },threadPool);

        System.out.println(completableFuture.get());
        threadPool.shutdown();
    }
}

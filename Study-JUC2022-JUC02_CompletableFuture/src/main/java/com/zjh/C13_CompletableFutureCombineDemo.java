package com.zjh;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author: JingDe
 * @date: 2024/9/7 14:50
 * @description:
 * @params:
 * @return:
 */
public class C13_CompletableFutureCombineDemo {
    public static void main(String[] args) {
        CompletableFuture<Integer> completableFuture1 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "\t --- starting");
            try {TimeUnit.MILLISECONDS.sleep(20);} catch (InterruptedException e) { e.printStackTrace();}
            return 10;
        });
        CompletableFuture<Integer> completableFuture2 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "\t --- starting");
            try {TimeUnit.MILLISECONDS.sleep(110);} catch (InterruptedException e) { e.printStackTrace();}
            return 20;
        });
        CompletableFuture<Integer> result = completableFuture1.thenCombine(completableFuture2, (x, y) -> {
            System.out.println("combining...");
            return x + y;
        });

        System.out.println(result.join());
    }
}

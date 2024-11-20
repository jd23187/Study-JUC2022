package com.zjh;

import java.util.concurrent.CompletableFuture;

/**
 * @author: JingDe
 * @date: 2024/9/6 17:42
 * @description:
 * @params:
 * @return:
 */
public class C10_CompletableFutureAPI3Demo {
    public static void main(String[] args) {
        // CompletableFuture.supplyAsync(() -> {
        //     return 1;
        // }).thenApply(f -> {
        //     return f + 2;
        // }).thenApply(f -> {
        //     return f + 3;
        // }).thenAccept(System.out::println);

        System.out.println(CompletableFuture.supplyAsync(()->"resultA").thenRun(()->{}).join());
        System.out.println(CompletableFuture.supplyAsync(()->"resultA").thenAccept((s)->{
            System.out.println(s);
        }).join());
        System.out.println(CompletableFuture.supplyAsync(()->"resultA ").thenApply(r->r + "resultB").join());
    }
}

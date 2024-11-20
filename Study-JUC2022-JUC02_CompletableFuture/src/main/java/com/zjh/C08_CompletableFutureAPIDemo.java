package com.zjh;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author: JingDe
 * @date: 2024/9/6 16:57
 * @description:
 * @params:
 * @return:
 */
public class C08_CompletableFutureAPIDemo {
    public static void main(String[] args) {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            //暂停几秒钟线程
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "abc";
        });
        //System.out.println(completableFuture.get());
        //system.out.println(completableFuture.get(2L,TimeUnit.SECONDS));
        //system.out.println(completableFuture.join());

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(completableFuture.getNow("xxx"));

        //是否打断 get() 方法立即返回括号中的值，返回 true 表示打断了获取异步线程结果的操作，直接返回 value 值。
        System.out.println(completableFuture.complete("completeValue") + "\t" + completableFuture.join());
        //abc
        //false	abc

        // xxx
        // true	completeValue
    }
}

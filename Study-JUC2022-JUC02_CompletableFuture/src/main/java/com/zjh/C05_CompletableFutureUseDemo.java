package com.zjh;

import java.util.concurrent.*;

/**
 * @author: JingDe
 * @date: 2024/9/6 15:22
 * @description:
 * @params:
 * @return:
 */
public class C05_CompletableFutureUseDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // future1();

        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        try {
            CompletableFuture.supplyAsync(() -> {
                System.out.println(Thread.currentThread().getName() + "---- come in");
                int result = ThreadLocalRandom.current().nextInt(10);
                //暂停几秒线程
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("----1s后出结果：" + result);
                if (result > 5) {
                    int i = 10 / 0;
                }
                return result;
            }, threadPool).whenComplete((v, e) -> {
                if (e == null) {
                    System.out.println("---计算完成，更新系统UpdateValue：" + v);
                }
            }).exceptionally((e) -> {
                e.printStackTrace();
                System.out.println("异常情况：" + e.getCause() + "\t" + e.getMessage());
                return null;
            });

            System.out.println(Thread.currentThread().getName() + "线程先去忙其他任务");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }


        //主线程不要立刻结束，否则compLetabteFuture默认使用的线程池会立刻关闭：暂停3秒钟线程
        // try {
        //     TimeUnit.SECONDS.sleep(1);
        // } catch (InterruptedException e) {
        //     e.printStackTrace();
        // }
    }


    private static void future1() throws InterruptedException, ExecutionException {
        CompletableFuture<Integer> completeableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "---- come in");
            int result = ThreadLocalRandom.current().nextInt(10);
            //暂停几秒线程
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("----1s后出结果：" + result);
            return result;
        });

        System.out.println(Thread.currentThread().getName() + "线程先去忙其他任务");
        System.out.println(completeableFuture.get());
    }
}

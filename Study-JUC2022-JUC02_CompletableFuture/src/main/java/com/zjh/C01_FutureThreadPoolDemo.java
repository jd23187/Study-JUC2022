package com.zjh;

import java.util.concurrent.*;

public class C01_FutureThreadPoolDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        // 3个任务，目前开启多个异步任务线程来处理，请问耗时多少？

        // m1();

        ExecutorService threadPool1 = Executors.newFixedThreadPool(3);

        long startTime = System.currentTimeMillis();
        FutureTask<String> futureTask1 = new FutureTask<String>(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "task1 over";
        });
        threadPool1.submit(futureTask1);
        FutureTask<String> futureTask2 = new FutureTask<String>(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "task2 over";
        });
        threadPool1.submit(futureTask2);
        try {
            TimeUnit.MILLISECONDS.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("----costTime:" + (endTime - startTime) + "毫秒");
        System.out.println(Thread.currentThread().getName() + "\t -----end");

        threadPool1.shutdown();

        // ThreadNum3();
        // FutureBlock();
        // FutureDone();
    }

    // 普通3个线程逐步执行
    private static void m1() {
        long startTime = System.currentTimeMillis();

        //暂停毫秒
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            TimeUnit.MILLISECONDS.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            TimeUnit.MILLISECONDS.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long endTime = System.currentTimeMillis();
        System.out.println("----costTime:" + (endTime - startTime) + "毫秒");
        System.out.println(Thread.currentThread().getName() + "\t -----end");
    }
}

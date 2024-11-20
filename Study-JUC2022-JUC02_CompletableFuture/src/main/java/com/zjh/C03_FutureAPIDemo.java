package com.zjh;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author: JingDe
 * @date: 2024/9/5 13:53
 * @description:
 * @params:
 * @return:
 */
public class C03_FutureAPIDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        FutureTask<String> futureTask1 = new FutureTask<String>(() -> {
            System.out.println(Thread.currentThread().getName() + "\t ------ come in");
            //暂停几秒线程
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "task1 over";
        });
        Thread t1 = new Thread(futureTask1, "t1");
        t1.start();

        System.out.println(Thread.currentThread().getName() + "\t ---忙其他任务了");

        //1、阻塞
        // System.out.println(futureTask1.get()); //不见不散，非要等到结果才会离开，不管你是否计算完成，容易程序堵塞。
        // System.out.println(futureTask1.get(3, TimeUnit.SECONDS));

        // 2、轮询
        while (true){
            if (futureTask1.isDone()){
                System.out.println(futureTask1.get());
                break;
            }else {
                //暂停几秒
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("正在加快执行...别催");
            }
        }
    }
}

/*
 * 1.get容易导致阻塞，一般建议放在程序后面，一旦调用 不见不散，非要等到结果才会离开，不管你是否计算完成，容易程序堵塞。
 * 2.假如我不愿意等待很长时间，我希望过时不候，可以自动离开。
 * */

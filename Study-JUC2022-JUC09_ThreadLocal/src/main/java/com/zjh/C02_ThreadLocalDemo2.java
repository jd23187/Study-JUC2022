package com.zjh;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: JingDe
 * @date: 2024/10/7 20:46
 * @description:
 * @params:
 * @return:
 */
class MyData{
    ThreadLocal<Integer> threadLocalField = ThreadLocal.withInitial(()->0);
    public void add(){
        threadLocalField.set(threadLocalField.get()+1);
    }
}

/*
* ：【强制】必须回收自定义的ThreadLocal，线程经常会被复用，
* 如果不清理自定义的ThreadLocal变量，可能会影响后续业务逻辑和造成内存泄露等问题。
* 尽量在代理中使用try-finally 块进行回收。
* */
public class C02_ThreadLocalDemo2 {
    public static void main(String[] args) {
        MyData myData = new MyData();
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        try {
            for (int i = 0; i < 10; i++) {
                threadPool.submit(()->{
                    try {
                        Integer beforeInt = myData.threadLocalField.get();
                        myData.add();
                        Integer afterInt = myData.threadLocalField.get();
                        System.out.println(Thread.currentThread().getName()+"\t"+"beforeInt:"+beforeInt+" afterInt:"+afterInt);
                    }finally {
                        myData.threadLocalField.remove();
                    }
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }
    }
}

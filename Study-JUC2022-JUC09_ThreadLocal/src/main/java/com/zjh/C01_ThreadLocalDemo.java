package com.zjh;

import java.util.Random;
import java.util.concurrent.TimeUnit;

//资源类
class House {
    int saleCount = 0;

    public synchronized void saleHouse() {
        ++saleCount;
    }

    // 太繁琐
    // ThreadLocal<Integer> saleVolume= new ThreadLocal<Integer>(){
    //     @Override
    //     protected Integer initialValue() {
    //         return 0;
    //     }
    // };
    ThreadLocal<Integer> saleVolume = ThreadLocal.withInitial(()->0);
    public void saleVolumeByThreadLocal(){
        saleVolume.set(saleVolume.get()+1);
    }
}

/*
 * 需求1：5个销售卖房子，集团高层只关心销售总量的准确统计数。
 *
 * 需求2：5个销售卖完随机数房子，各自独立销售额度，自已业绩按提成走，分灶吃饭，各个销售自动手，丰衣是食
 * */
public class C01_ThreadLocalDemo {
    public static void main(String[] args) {
        House house = new House();
        for (int i = 1; i <= 5; i++) {
            new Thread(() -> {
                int size = new Random().nextInt(5) + 1;
                // System.out.println(size);
                try {
                    for (int j = 1; j <= size; j++) {
                        house.saleHouse();
                        house.saleVolumeByThreadLocal();
                    }
                }finally {
                    house.saleVolume.remove();
                }
                System.out.println(Thread.currentThread().getName()+"\t 号销售卖出："+house.saleVolume.get());
            },String.valueOf(i)).start();
        }

        try {TimeUnit.MILLISECONDS.sleep(300);} catch (InterruptedException e) { e.printStackTrace();}
        System.out.println(Thread.currentThread().getName()+"\t 共计卖出多少套："+house.saleCount);
    }
}

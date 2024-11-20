package com.zjh.volatiles;

import java.util.concurrent.TimeUnit;

/**
 * @author: JingDe
 * @date: 2024/9/11 14:33
 * @description:
 * @params:
 * @return:
 */
class MyNumber{
    volatile int number;
    public synchronized void addPlusPlus(){
        number++;
    }
}
public class C02_VolatileNoAtomicDemo {
    public static void main(String[] args){
        MyNumber myNumber = new MyNumber();
        for (int i = 1; i <= 10; i++) {
            new Thread(()->{
                for (int j =1; j<= 1000; j++){
                    myNumber.addPlusPlus();
                }
            },String.valueOf(i)).start();
        }

        try {TimeUnit.MILLISECONDS.sleep(2000);} catch (InterruptedException e) { e.printStackTrace();}

        System.out.println(myNumber.number);
    }
}

package com.zjh;

import java.lang.ref.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author: JingDe
 * @date: 2024/10/8 14:24
 * @description:
 * @params:
 * @return:
 */
class MyObject{
    // 这个方法一般不用复写，我们只是为了教学给大家演示案例做说明
    @Override
    protected void finalize() throws Throwable {
        //finalize的通常目的是在对象被不可撤销地丢弃之前执行清理操作。
        System.out.println("-----invoke finalize method~!!!");
    }
}
public class C03_ReferenceDemo {
    public static void main(String[] args) {
        // strongReference();
        // softReference();
        // weakReference();
        MyObject myObject = new MyObject();
        ReferenceQueue<MyObject> referenceQueue = new ReferenceQueue<>();
        PhantomReference<MyObject> phantomReference = new PhantomReference<>(myObject, referenceQueue);

        // System.out.println(phantomReference.get());
        List<byte[]> list = new ArrayList<>();

        new Thread(()->{
            while (true){
                list.add(new byte[1* 1024 * 1024]);
                try {TimeUnit.MILLISECONDS.sleep(500);} catch (InterruptedException e) { e.printStackTrace();}
                System.out.println(phantomReference.get()+"\t"+"list add ok");
            }
        },"t1").start();

        new Thread(()->{
            while (true){
                Reference<? extends MyObject> reference = referenceQueue.poll();
                if (reference!=null){
                    System.out.println("-------有虚对象回收加入了队列");
                    break;
                }
            }
        },"t2").start();

    }

    private static void weakReference() {
        WeakReference<MyObject> weakReference = new WeakReference<>(new MyObject());
        System.out.println("gc before内存够用: "+weakReference.get());

        System.gc();

        try {TimeUnit.MILLISECONDS.sleep(500);} catch (InterruptedException e) { e.printStackTrace();}
        System.out.println("gc after内存够用: "+weakReference.get());
    }

    private static void softReference() {
        SoftReference<MyObject> softReference = new SoftReference<>(new MyObject());
        // System.out.println("--------softReference: "+softReference.get());

        System.gc();

        try {TimeUnit.MILLISECONDS.sleep(500);} catch (InterruptedException e) { e.printStackTrace();}
        System.out.println("gc after内存够用: "+softReference.get());

        try {
            byte[] buffer = new byte[20 * 1024 * 1024];
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            System.out.println("gc after内存不够用: "+softReference.get());
        }
    }

    private static void strongReference() {
        MyObject myObject = new MyObject();
        System.out.println("gc before: "+myObject);

        myObject=null;
        System.gc();//人工开启GC，一般不用

        try {TimeUnit.MILLISECONDS.sleep(500);} catch (InterruptedException e) { e.printStackTrace();}
        System.out.println("gc after: "+myObject);
    }
}

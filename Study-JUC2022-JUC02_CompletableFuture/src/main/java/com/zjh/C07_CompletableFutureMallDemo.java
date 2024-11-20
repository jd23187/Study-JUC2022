package com.zjh;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author: JingDe
 * @date: 2024/9/6 16:16
 * @description: - 1 需求说明
 * - 1.1 同一款产品，同时搜索出同款产品在各大电商平台的售价;
 * - 1.2 同一款产品，同时搜索出本产品在同一个电商平台下，各个入驻卖家售价是多少
 * - 2 输出返回:
 * - 出来结果希望是同款产品的在不同地方的价格清单列表，返回一个List<String>
 * - 《mysql》in jd price is 88.05
 * - 《mysql》in dangdang price is 86.11
 * - 《mysql》in taobao price is 90.43
 * - 3 技术要求
 * - 3.1函数式编程
 * - 3.2链式编程
 * - 3.3Stream流式计算
 * @params:
 * @return:
 */
public class C07_CompletableFutureMallDemo {
    static List<NetMall> list = Arrays.asList(
            new NetMall("jd"),
            new NetMall("dangdang"),
            new NetMall("taobao"),
            new NetMall("pdd"),
            new NetMall("xianyu")
    );

    // step by step一家家搜查
    public static List<String> getPrice(List<NetMall> list, String productName) {
        return list.stream()
                .map(netMall ->
                        String.format(productName + " in %s price is %.2f",
                                netMall.getNetMallName(),
                                netMall.calcPrice(productName)))
                .collect(Collectors.toList());
    }

    // List<NetMall> --> List<completableFuture<String>> ---> List<string>
    public static List<String> getPriceByCompletableFuture(List<NetMall> list, String productName) {
        return list.stream().map(netMall ->
                        CompletableFuture.supplyAsync(() ->
                                String.format(productName + " in %s price is %.2f",
                                        netMall.getNetMallName(),
                                        netMall.calcPrice(productName))))
                .collect(Collectors.toList())
                .stream()
                .map(s -> s.join())
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        List<String> list1 = getPrice(list, "mysql");
        for (String element : list1) {
            System.out.println(element);
        }
        long endTime = System.currentTimeMillis();
        // 串行编程调用需要耗时3秒以上
        System.out.println("-----costTime: " + (endTime - startTime) + "毫秒");


        System.out.println("-------------------");


        long startTime2 = System.currentTimeMillis();
        List<String> list2 = getPriceByCompletableFuture(list, "mysql");
        for (String element : list2) {
            System.out.println(element);
        }
        long endTime2 = System.currentTimeMillis();
        // 并行编程调用需要耗时仅需要1秒以上
        System.out.println("-----costTime2: " + (endTime2 - startTime2) + "毫秒");
    }
}

class NetMall {

    @Getter
    private String netMallName;

    public NetMall(String netMallName) {
        this.netMallName = netMallName;
    }

    public double calcPrice(String productName) {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //nextDouble:[0,1) 、
        // System.out.println(ThreadLocalRandom.current().nextDouble() * 2 + "mysql".charAt(0));
        return ThreadLocalRandom.current().nextDouble() * 2 + productName.charAt(0);
    }
}


package com.zjh;

import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;
import java.util.function.LongBinaryOperator;

/**
 * @author: JingDe
 * @date: 2024/9/30 15:15
 * @description:
 * @params:
 * @return:
 */
public class C06_LongAdderAPIDemo {
    public static void main(String[] args) {
        // LongAdder longAdder = new LongAdder();
        //
        // longAdder.increment();
        // longAdder.increment();
        // longAdder.increment();
        //
        // System.out.println(longAdder.sum());

        // LongAccumulator longAccumulator = new LongAccumulator((x,y)->x+y,0);
        LongAccumulator longAccumulator = new LongAccumulator(new LongBinaryOperator() {
            @Override
            public long applyAsLong(long left, long right) {
                return left + right;
            }
        }, 0);
        longAccumulator.accumulate(1);//1
        longAccumulator.accumulate(3);//4
        longAccumulator.accumulate(10);//14
        System.out.println(longAccumulator.get());
    }
}


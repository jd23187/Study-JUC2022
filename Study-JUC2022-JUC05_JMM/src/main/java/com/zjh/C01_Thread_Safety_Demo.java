package com.zjh;

public class C01_Thread_Safety_Demo {
    // private int value = 0;
    // public int getValue() {
    //     return value;
    // }
    //
    // public int setValue() {
    //     return ++value;
    // }

    // 修复01
    // 把getter/setter方法都定义为synchronized方法
    // private int value = 0;
    // public synchronized int getValue() {
    //     return value;
    // }
    //
    // public synchronized int setValue() {
    //     return ++value;
    // }

    // 修复02
    // 把value定义为volatile变量，由于setter方法对value的修改不依赖value的原值，满足volatile关键字使用场景
    // 理由：利用volatile保证读取操作的可见性：利用synchronized保证复合操作的原子性结合使用锁和volatile变量来减少同步的开销
    private volatile int value = 0;

    public int getValue() {
        return value;
    }

    public synchronized int setValue() {
        return ++value;
    }
    public static void main(String[] args) {
        /*
        * 假设存在线程A和B，线程A先（时间上的先后）调用了setValue()，然后线程B调用了同一个对象的getValue()，那么线程B收到的返回值是什么？
        *
        * 我们就这段简单的代码一次分析happens-before的规则（规则5、6、7、8可以忽略，因为他们和这段代码毫无关系）：
        * 1由于两个方法是由不同的线程调用，不在同一个线程中，所以肯定不满足程序次序规则；
        * 2两个方法都没有使用锁，所以不满足锁定规则；
        * 3变量不是用volatile修饰的，所以volatile变量规则不满足：
        * 4传递规则肯定不满足；
        *
        * 所以我们无法通过happens-before原则推导出before线程B，虽然可以确认在时间上线程A优先于线程B指定，
        * 但就是无法确认线程B获得的结果是什么，所以这段代码不是线程安全的。那么怎么修复这段代码呢？
        * */
    }
}

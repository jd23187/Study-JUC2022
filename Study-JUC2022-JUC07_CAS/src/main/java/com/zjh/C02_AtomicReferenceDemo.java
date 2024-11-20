package com.zjh;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author: JingDe
 * @date: 2024/9/11 16:56
 * @description:
 * @params:
 * @return:
 */
@Getter
@ToString
@AllArgsConstructor
class User {
    String userName;
    int age;
}

public class C02_AtomicReferenceDemo {
    public static void main(String[] args) {
        AtomicReference<User> atomicReference = new AtomicReference<>();

        User z3 = new User("z3", 23);
        User li4 = new User("li4", 23);

        atomicReference.set(z3);

        System.out.println(atomicReference.compareAndSet(z3, li4) + "\t" + atomicReference.get().toString());
        System.out.println(atomicReference.compareAndSet(z3, li4) + "\t" + atomicReference.get().toString());
    }
}

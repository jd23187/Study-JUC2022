package com.zjh;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author: JingDe
 * @date: 2024/9/6 15:59
 * @description: get()和join()的区别：是否在编译器异常检测
 * @params:
 * @return:
 */
public class C06_CompletableFutureMallDemo {
    // public static void main(String[] args) throws ExecutionException, InterruptedException {
    //     // chainTest();
    //     CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
    //         return "hello 1234";
    //     });
    //     System.out.println(completableFuture.get());
    // }

    public static void main(String[] args){
        // chainTest();
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            return "hello 1234";
        });
        System.out.println(completableFuture.join());
    }

    private static void chainTest() {
        Student student = new Student();

        student.setId(1);
        student.setStudentName("zhang");
        student.setMajor("CS");

        student.setId(2).setStudentName("li").setMajor("CS");
    }
}

@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
class Student{
    private Integer id;
    private String studentName;
    private String major;
}

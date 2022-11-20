package com.lhz;

import java.util.concurrent.*;

/**
 *   public static CompletableFuture<Void> runAsync(Runnable runnable)无返回值构造CompletableFuture
 *
 *   CompletableFuture.supplyAsync(() -> {
 *             return "aaa";
 *         }, executorService);传入供给型接口有返回值；
 */
public class CompletableFutureBuildDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(3);//线程池

        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName());
            System.out.println("aaaa");
        },executorService);
        System.out.println(voidCompletableFuture.get());
        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> {
            return "aaa";
        }, executorService);

        System.out.println(stringCompletableFuture.get());
    }
}

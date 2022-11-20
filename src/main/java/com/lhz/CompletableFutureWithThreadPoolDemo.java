package com.lhz;

import java.util.concurrent.*;

/**
 * 如果不传线程池，就是默认的线程池
 * 如果传了线程池thenRUn也会用自定义的线程池
 * thenRunAsync还是默认的线程池
 */

public class CompletableFutureWithThreadPoolDemo {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        try {
            CompletableFuture.supplyAsync(()->{
                try {
                    TimeUnit.MILLISECONDS.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("1号任务"+"\t"+ Thread.currentThread().getName());
                return "abcd";
            },executorService).thenRun(()->{
                try {
                    TimeUnit.MILLISECONDS.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("2号任务"+"\t"+ Thread.currentThread().getName());

            }).thenRunAsync(()->{
                try {
                    TimeUnit.MILLISECONDS.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("3号任务"+"\t"+ Thread.currentThread().getName());  });
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}

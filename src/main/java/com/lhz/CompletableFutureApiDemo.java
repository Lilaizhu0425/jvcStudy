package com.lhz;

import java.lang.reflect.ParameterizedType;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * get()不见不散
 * get(long timeout,TimeUnit unit) 过期不候
 * join()与get一样
 * getNow(T ValuelfAbsent)立即返回结果 计算完返回计算后的结果 没计算完返回设定的valuelfAbsent结果
 *
 */
public class CompletableFutureApiDemo {
    /**
     * 对计算结果进行处理//任务a执行完执行b，b需要a的结果，同时任务b有返回值
     *
     * @param args
     */
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);


        CompletableFuture.supplyAsync(()->{
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 1;
        },executorService).thenApply(f->{
            System.out.println("3333");
            return f+3;
        }).thenApply(f->{
            System.out.println("aaa");
            return f;
        }).whenComplete((t,v)->{

            if(v==null){
                System.out.println("计算结果值"+t);
            }
        }).exceptionally(t->{
            t.printStackTrace();
            return null;
        });
    }

    /**
     *      * 对计算结果进行处理//任务a执行完执行b，b需要a的结果，任务b无返回值
     */
    public static void  test1(){

        CompletableFuture.supplyAsync(()->{
            return 1;
        }).thenApply(t->{
            return t+1;
        }).thenAccept(t->{
            System.out.println(t);
        });


    }

    /**
     * 任务a执行完执行b，并且b不需要a的结果
     */
    public static void  test12(){

        CompletableFuture.supplyAsync(()->{
            return 1;
        }).thenRun(()->{
            System.out.println("aaa");
        });

    }

}

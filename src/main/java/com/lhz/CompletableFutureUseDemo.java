package com.lhz;

import java.util.concurrent.*;


public class CompletableFutureUseDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        try {

            CompletableFuture<Integer> exceptionally = CompletableFuture.supplyAsync(() -> {
                System.out.println(Thread.currentThread().getName() + "  comein");
                int i = ThreadLocalRandom.current().nextInt(10);

                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("-----1秒后结果" + i);
                return i;
            },executorService).whenComplete((t, v) -> {
                if (v == null) {
                    System.out.println("计算完成 " + t);
                }
            }).exceptionally(e -> {
                e.printStackTrace();
                System.out.println("异常" + e.getCause());
                return null;

            });
            System.out.println(Thread.currentThread().getName()+"先忙其他任务");
            //主线程不结束

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            executorService.shutdown();
        }



    }

    private static void future1() throws InterruptedException, ExecutionException {
        CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "  comein");
            int i = ThreadLocalRandom.current().nextInt(10);

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("-----1秒后结果" + i);
            return i;
        });
        System.out.println(Thread.currentThread().getName()+"先忙其他任务");
        System.out.println(integerCompletableFuture.get());
    }
}

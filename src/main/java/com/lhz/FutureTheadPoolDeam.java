package com.lhz;

import java.util.concurrent.*;

public class FutureTheadPoolDeam {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        long l = System.currentTimeMillis();

        ExecutorService executorService = Executors.newFixedThreadPool(3);


        FutureTask<String> stringFutureTask = new FutureTask<>(() -> {
            TimeUnit.MILLISECONDS.sleep(500);
            return "tesk1 over";
        });
        Future<?> submit = executorService.submit(stringFutureTask);

        FutureTask<String> stringFutureTask1 = new FutureTask<>(() -> {
            TimeUnit.MILLISECONDS.sleep(300);
            return "tesk1 over";
        });
        executorService.submit(stringFutureTask1);

        FutureTask<String> stringFutureTask2 = new FutureTask<>(() -> {
            TimeUnit.MILLISECONDS.sleep(300);
            return "tesk1 over";
        });
        executorService.submit(stringFutureTask2);
        executorService.shutdown();
        System.out.println(System.currentTimeMillis()-l);
        System.out.println(stringFutureTask.get());




    }

    private static void m1() throws InterruptedException {
        long l = System.currentTimeMillis();
        TimeUnit.MILLISECONDS.sleep(500);
        TimeUnit.MILLISECONDS.sleep(300);

        TimeUnit.MILLISECONDS.sleep(300);
        System.out.println(System.currentTimeMillis()-l);
    }
}

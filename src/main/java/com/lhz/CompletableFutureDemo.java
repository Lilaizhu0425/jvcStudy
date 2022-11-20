package com.lhz;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CompletableFutureDemo   {

    public static void main(String[] args) {
        FutureTask futureTask = new FutureTask<>(new mytherd());
        Thread thread = new Thread();

        Thread thread1 = new Thread(futureTask, "t1");
        thread1.start();
        try {
            Object o = futureTask.get();
            System.out.println(o);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }


}
class mytherd implements Callable<String>{

    @Override
    public String call() throws Exception {
        System.out.println("come in call()");

        return "hello callobel";
    }
}
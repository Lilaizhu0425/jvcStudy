package com.lhz;

import org.omg.CORBA.TIMEOUT;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
//future 对结果获取不是很友好，只能通过阻或轮训方式
public class FutureTaskApIDeam {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {

        FutureTask<String> stringFutureTask = new FutureTask<>(() -> {
            System.out.println(Thread.currentThread().getName() + "\t------comein");
            TimeUnit.SECONDS.sleep(5);
            return "task over";
        });
        Thread thread = new Thread(stringFutureTask, "a");

        thread.start();
        //System.out.println( stringFutureTask.get(3, TimeUnit.SECONDS));//不见不散，非要等到结果，不管你你是否计算完成get容易阻塞

        System.out.println(Thread.currentThread().getName());
        while (true){
            if(stringFutureTask.isDone()){//轮训方法访问是否执行
                System.out.println(stringFutureTask.get());
                break;
            }else {
                TimeUnit.MILLISECONDS.sleep(300);
                System.out.println("正在处理中");
            }
        }



    }
}

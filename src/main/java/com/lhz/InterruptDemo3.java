package com.lhz;

import java.util.concurrent.TimeUnit;

public class InterruptDemo3 {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println(Thread.currentThread().getName() + "\t " + "中断标志位：" + Thread.currentThread().isInterrupted() + "程序停止");
                    break;

                }
                try {
                    TimeUnit.MILLISECONDS.sleep(200);//程序中断如果遇到sleep会抛出一个异常同时会清除中断标志位
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    e.printStackTrace();
                }
                System.out.println("---------hello");

            }
        }, "t1");
        thread.start();

        TimeUnit.MILLISECONDS.sleep(20);
        new Thread(()->{
            thread.interrupt();
        }).start();
    }
}

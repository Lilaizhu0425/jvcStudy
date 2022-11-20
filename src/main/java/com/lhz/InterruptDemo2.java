package com.lhz;

import java.util.concurrent.TimeUnit;

public class InterruptDemo2 {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 300; i++) {
                System.out.println("-----" + i);
            }

        }, "t1");
        thread.start();
        System.out.println("t1默认中断标识"+thread.isInterrupted());
        TimeUnit.MILLISECONDS.sleep(2);
        thread.interrupt();
        System.out.println("t1线程调用interrupt后中断标识"+thread.isInterrupted());//处于正常活动状态，那么会将该线程的中断标志设置为 true
        TimeUnit.MILLISECONDS.sleep(2);
        TimeUnit.MILLISECONDS.sleep(2000);
        thread.interrupt();

        System.out.println("t1线程调用interrupt后中断标识"+thread.isInterrupted());//处于正常活动状态，那么会将该线程的中断标志设置为 true 2秒后该线程已经结束的



    }
}

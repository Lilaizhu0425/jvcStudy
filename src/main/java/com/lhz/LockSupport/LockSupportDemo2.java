package com.lhz.LockSupport;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class LockSupportDemo2 {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {

            System.out.println(Thread.currentThread().getName() + "\t----come in");
            LockSupport.park();
            System.out.println(Thread.currentThread().getName() + "\t ---被唤醒");


        }, "t1");
        thread.start();
        TimeUnit.SECONDS.sleep(2);
        new Thread(() -> {
            LockSupport.unpark(thread);

                System.out.println("发出通知");



        }, "t2").start();

    }
}

package com.lhz.LockSupport;

import java.util.concurrent.TimeUnit;

public class LockSupportDemo {
    /**
     * object 类中的线程等待与唤醒
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        Object o = new Object();
        new Thread(()->{
            synchronized (o){
                System.out.println(Thread.currentThread().getName()+"\t----come in");
                try {
                    o.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();

                }
                System.out.println(Thread.currentThread().getName()+"\t ---被唤醒");
            }

        },"t1").start();
        TimeUnit.SECONDS.sleep(2);
        new Thread(()->{
            synchronized (o){
                o.notify();
                System.out.println("发出通知");



            }
        },"t2").start();

    }
}

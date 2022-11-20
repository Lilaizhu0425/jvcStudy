package com.lhz;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 *
 * 实例方法 public void interrupt 仅仅设置线程的中断状态为true，发起一个协商而不会立刻停止的线程
 * public static boolean interrupt 判断线程是否被中断并清除当前中断状态
 *
 * public boolean inteerrupt 判断线程是否被中断
 *
 */

public class InterruptDemo {
    public  static  volatile  boolean isStop=false;
    public  static AtomicBoolean atomicBoolean=new AtomicBoolean(false);//原子布尔
    public static void main(String[] args) {
        //booleanTest();
        Thread thread = new Thread(() ->
        {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println(Thread.currentThread().getName() + "\t isStop被修改为true 程序停止");
                    break;
                }
                System.out.println("---hello volatile");
            }
        }, "t1");
        thread.start();
        try {
            TimeUnit.MILLISECONDS.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()->{
            thread.interrupt();

        },"t2").start();
    }

    private static void booleanTest() {
        Thread thread = new Thread(() ->
        {
            while (true) {
                if (isStop) {
                    System.out.println(Thread.currentThread().getName() + "\t isStop被修改为true 程序停止");
                    break;
                }
                System.out.println("---hello volatile");
            }
        }, "t1");
        thread.start();

        new Thread(()->{

            isStop=true;

        },"t2").start();
    }


}

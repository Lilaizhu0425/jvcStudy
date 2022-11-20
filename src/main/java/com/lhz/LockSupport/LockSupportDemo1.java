package com.lhz.LockSupport;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * condition 接口实现 线程的等呆与唤醒
 */
public class LockSupportDemo1 {
    public static void main(String[] args) throws InterruptedException {

        ReentrantLock reentrantLock = new ReentrantLock();
        Condition condition = reentrantLock.newCondition();
        new Thread(()->{
            reentrantLock.lock();
            try {
                System.out.println(Thread.currentThread().getName()+"\t---come in");
                condition.await();
                System.out.println("被唤醒");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                reentrantLock.unlock();
            }
        },"t1").start();
        TimeUnit.SECONDS.sleep(2);
        new Thread(()->{
            reentrantLock.lock();
            try {
                condition.signal();
                System.out.println("发出通知");
            }finally {
                reentrantLock.unlock();
            }
        },"t2").start();
    }
}

package com.lhz.cas;

import sun.misc.Unsafe;

import java.util.concurrent.atomic.AtomicInteger;

public class CASDemo {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);
        boolean b = atomicInteger.compareAndSet(4, 2000);
        System.out.println(b);
        int i = atomicInteger.get();

        System.out.println(i);


    }
}

package com.lhz;

import javax.sound.midi.Soundbank;

public class InterruptDemo4 {
    /**
     * 测试当前线程是否被中断，返回一个boolean并清除中断状态
     * 第二次调用中断状态已经清除 将返回一个false
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName()+"\t" +Thread.interrupted());
        System.out.println(Thread.currentThread().getName()+"\t" +Thread.interrupted());
        System.out.println("1");
        Thread.currentThread().interrupt();

        System.out.println(Thread.currentThread().getName()+"\t" +Thread.interrupted());
        System.out.println(Thread.currentThread().getName()+"\t" +Thread.interrupted());


    }
}

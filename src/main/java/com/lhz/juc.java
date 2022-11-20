package com.lhz;

import java.util.ArrayList;

public class juc {
    public static void main(String[] args) {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();

        Thread thread = new Thread(() -> {
            System.out.println("aa");
        });



        boolean daemon = thread.isDaemon();
        System.out.println(daemon);


    }
}

package com.lhz;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CompletableFutureMallDemo {
    static List<NetMall> list= Arrays.asList(new NetMall("jd"),new NetMall("dangdang"),new NetMall("taobao"));

    public static List<String> getPrice(List<NetMall> list,String productName){
        List<String> collect = list.stream().map(t -> String.format(productName + "in %s price is %.2f", t.getNetMallName(), t.calcPrice(productName))).collect(Collectors.toList());
        return collect;




    }
    public static List<String> getPriceByCompletableFuture(List<NetMall> list,String productName){
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        List<String> collect = list.stream().map(t -> CompletableFuture.supplyAsync(() -> {
            return String.format(productName + "in %s price is %.2f", t.getNetMallName(), t.calcPrice(productName));
        },executorService)).collect(Collectors.toList()).stream().map(t->t.join()).collect(Collectors.toList());

        executorService.shutdown();
        return collect;
    }

    public static void main(String[] args) {
        long l = System.currentTimeMillis();
        List<String> mysql = getPriceByCompletableFuture(list, "mysql");

        for (String s : mysql) {
            System.out.println(s);
        }

        long l1 = System.currentTimeMillis();
        System.out.println(l1-l);
    }

}


@Getter
@AllArgsConstructor

class NetMall{
    private String netMallName;
    public double calcPrice(String productName){

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return  ThreadLocalRandom.current().nextDouble()*2+productName.charAt(0);
    }

}

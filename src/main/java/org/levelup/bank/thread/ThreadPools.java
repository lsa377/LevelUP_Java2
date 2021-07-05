package org.levelup.bank.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class ThreadPools {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService es = Executors.newFixedThreadPool(4);
        int[] randomInts = new int[5000];
        Random r = new Random();
        for(int i = 0; i < randomInts.length; i++){
            randomInts[i] = r.nextInt(1000);
        }
        List<Future<Long>> futures = new ArrayList<>();
        for(int i = 0; i < 5000; i++) {
            //int copyI = i;
            //es.submit(() -> System.out.println(Thread.currentThread().getName() + " " + copyI));
            int start = i;
            int end = i + 9;
            Future<Long> futureSumOfNextTenElements = es.submit(() -> {
                        long sum = 0L;
                        for(int j = start; j < end; j++){
                            sum += randomInts[j];
                        }
                        return sum;
                    });
            futures.add(futureSumOfNextTenElements);
        }
        es.shutdown();
        long sum = 0L;
        for(Future<Long> futureSumOfNextTenElements : futures){
            sum+=futureSumOfNextTenElements.get();
        }
        System.out.println(sum);
    }
}

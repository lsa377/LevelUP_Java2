package org.levelup.bank.thread.counter;

import java.util.concurrent.atomic.AtomicInteger;

public class NonBlockingValueCounter {

    private AtomicInteger counter = new AtomicInteger(0);
    public void increment(){
        counter.incrementAndGet();
    }

    public int getCounter(){
        return counter.get();
    }
}

package org.levelup.bank.thread.counter;

public class ValueCounter {
    private int counter;
    public synchronized void increment(){
        counter++;
    }

    public int getCounter(){
        synchronized (this) {
            return counter;
        }
    }
}

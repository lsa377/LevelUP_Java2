package org.levelup.bank.thread.queue;

import lombok.SneakyThrows;

import java.util.LinkedList;

public class SyncronizedQueue implements Queue{

    private final Object empty = new Object();
    private final Object full = new Object();

    private final LinkedList<Duplicate> duplicates;
    private final int maxSize;

    public SyncronizedQueue(){
        this.duplicates = new LinkedList<>();
        this.maxSize = 10;
    }

    @Override
    @SneakyThrows
    public void putDuplicate(Duplicate duplicate) {
        synchronized (full) {
            while (duplicates.size() == maxSize) {
                full.wait();
            }
        }

        synchronized (empty) {
            duplicates.offer(duplicate);
            empty.notifyAll();
        }
    }


    @Override
    public Duplicate takeDuplicate() throws InterruptedException{
        synchronized (empty){
            while (duplicates.isEmpty()){
             empty.wait();
            }
        }

        synchronized (full) {
            Duplicate duplicate =  duplicates.poll();
            full.notifyAll();
            return duplicate;
        }
    }
}

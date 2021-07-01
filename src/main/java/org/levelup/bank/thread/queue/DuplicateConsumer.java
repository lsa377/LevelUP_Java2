package org.levelup.bank.thread.queue;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@RequiredArgsConstructor
public class DuplicateConsumer implements Runnable{
    private final Queue queue;

    @Override
    @SneakyThrows
    public void run() {
        boolean isInterrupted = false;
        while (!isInterrupted) {
            try {
                Duplicate duplicate = queue.takeDuplicate();
                System.out.println(Thread.currentThread().getName() + " processes duplicate " + duplicate.toString());
                Thread.sleep(200);
                isInterrupted = Thread.currentThread().isInterrupted();
            } catch (InterruptedException exc){
                System.out.println("Thread was interrupted while sleeping or wait: "+exc.getMessage());
                isInterrupted = true;
            }
        }
        System.out.println("Thread "+Thread.currentThread().getName()+ " finished");

    }
}

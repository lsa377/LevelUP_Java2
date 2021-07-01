package org.levelup.bank.thread.queue;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import java.util.Random;

@RequiredArgsConstructor
public class FindDuplicatesProducer implements Runnable{

    private final Queue queue;

    @Override
    @SneakyThrows
    public void run() {
        Random random = new Random();
        for(int i = 0;i < 50; i++){
            int originalId = random.nextInt(1000);
            int dulicateId = random.nextInt(1000) + 1000;
            Duplicate duplicate = new Duplicate(originalId,dulicateId);
            Thread.sleep(300);
            System.out.println(Thread.currentThread().getName() + " found duplicate " + duplicate.toString());
            queue.putDuplicate(duplicate);
        }
    }
}

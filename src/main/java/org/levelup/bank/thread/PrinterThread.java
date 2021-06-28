package org.levelup.bank.thread;

import lombok.SneakyThrows;


public class PrinterThread extends Thread{
    @Override
    @SneakyThrows
    public void run() {
        for(int i = 0; i < 10; i++){
            System.out.println(getName() + ": "+i);
            Thread.sleep(400);
        }
    }
}

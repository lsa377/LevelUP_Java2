package org.levelup.bank.thread;

import lombok.SneakyThrows;


public class ThreadExamples {
    @SneakyThrows
    public static void main(String[] args) {
        Thread printer = new PrinterThread();
        // printer.setDaemon(true);
        printer.start();

        printer.join();

        Thread.sleep(3000);
        System.out.println("Thread main is over");

    }
}

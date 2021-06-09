package org.levelup.bank.reflection.profiling;

public class ProfilingApp {
    public static void main(String[] args) {
        PrinterFactory.getPrinter().printInformation();
        PrinterFactory.getPrinter().printStatus(true);
    }
}
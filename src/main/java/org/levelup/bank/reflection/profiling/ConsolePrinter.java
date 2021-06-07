package org.levelup.bank.reflection.profiling;

public class ConsolePrinter implements Printer{
    @Override
    @Profiling
    public void printInformation() {
        System.out.println("Ho-ho-ho, motherfuckers!");
    }
}

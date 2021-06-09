package org.levelup.bank.reflection.profiling;

public class ConsolePrinter implements Printer{
    @Override
    @Profiling
    public void printInformation() {
        System.out.println("Ho-ho-ho, motherfuckers!");
    }

    @Override
    public void printStatus(boolean status){
        if(status) {
            System.out.println("Status: ok");
        }
        else {
            System.out.println("Status: error!");
        }
    }
}

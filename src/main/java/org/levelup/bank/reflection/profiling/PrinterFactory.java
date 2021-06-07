package org.levelup.bank.reflection.profiling;

import java.lang.reflect.Proxy;

public class PrinterFactory {
    public static Printer getPrinter(){
        return (Printer) Proxy.newProxyInstance(
                ConsolePrinter.class.getClassLoader(),
                ConsolePrinter.class.getInterfaces(),
                new ProfilingInvocationHandler(new ConsolePrinter())
        );
    }
}

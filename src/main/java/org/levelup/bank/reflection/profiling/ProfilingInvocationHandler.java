package org.levelup.bank.reflection.profiling;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ProfilingInvocationHandler implements InvocationHandler {

    private Object original;
    public ProfilingInvocationHandler(Object original){
        this.original = original;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long start = System.nanoTime();
        Object result = method.invoke(original,args);
        System.out.println("Exec time (nanoseconds): "+(System.nanoTime() - start));
        return result;
    }
}

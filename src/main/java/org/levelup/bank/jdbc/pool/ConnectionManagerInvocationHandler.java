package org.levelup.bank.jdbc.pool;

import org.levelup.bank.reflection.profiling.Profiling;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ConnectionManagerInvocationHandler implements InvocationHandler {
    private Object original;
    public ConnectionManagerInvocationHandler(Object original){
        this.original = original;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long start = System.nanoTime();
        Object result = method.invoke(original,args);
        if(method.getAnnotation(ConnectionTime.class) != null) {
            System.out.println("Connection "+result.toString()+" got from pool in "+(System.nanoTime() - start)+" nanoseconds");
        }
        return result;
    }

}

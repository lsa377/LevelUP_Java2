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
        long finish = System.nanoTime();
        Class<?> originalClass = Class.forName(original.getClass().getName());
        for(Method method1 : (originalClass.getDeclaredMethods())) {
            Profiling annotation = method1.getAnnotation(Profiling.class);
            if(annotation != null && method.getName().equals(method1.getName())){
                System.out.println("Method '" + method.getName() + "' of the class '"+original.getClass().getName()+"' executed in "+(finish - start)+" nanoseconds");
            }
        }
        return result;
    }
}

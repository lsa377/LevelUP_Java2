package org.levelup.bank.jdbc.pool;

import lombok.RequiredArgsConstructor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.Connection;

@RequiredArgsConstructor
public class ConnectionCloseInvocationHandler implements InvocationHandler {
    private final Connection realConnection;
    private final ConnectionPool pool;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(method.getName().equals("close")){
            pool.releaseConnection((Connection) proxy);
            return null;
        }
        return method.invoke(realConnection, args);
    }
}

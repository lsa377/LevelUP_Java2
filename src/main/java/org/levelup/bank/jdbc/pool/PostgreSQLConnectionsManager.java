package org.levelup.bank.jdbc.pool;

import org.levelup.bank.jdbc.ConnectionManager;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgreSQLConnectionsManager implements ConnectionManager {

    private ConnectionPool pool;

    public PostgreSQLConnectionsManager(){
        this.pool = new ConnectionPool();
    }

    @Override
    public Connection openConnection() throws SQLException {
        Connection connection = pool.acquireConnection();
        if(connection == null){
            connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/bank_application",
                    "postgres", "1qazXSW@");
            connection = proxyConnection(connection);
            pool.putConnectionToPool(connection);
        }
       return connection;
    }

    private Connection proxyConnection(Connection realConnection){
        return (Connection) Proxy.newProxyInstance(
          realConnection.getClass().getClassLoader(),
          realConnection.getClass().getInterfaces(),
          new ConnectionCloseInvocationHandler(realConnection, pool)
        );
    }
}
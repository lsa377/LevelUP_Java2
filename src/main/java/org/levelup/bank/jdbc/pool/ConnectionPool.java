package org.levelup.bank.jdbc.pool;

import java.sql.Connection;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Set;

public class ConnectionPool {

    private final IdentityHashMap<Connection,Boolean> pool = new IdentityHashMap<>();

    public Connection acquireConnection(){
        Set<Map.Entry<Connection,Boolean>> entries = pool.entrySet();
        for (Map.Entry<Connection,Boolean> entry :entries){
            if(!entry.getValue()){
                entry.setValue(true);
                return entry.getKey();
            }
        }
        return null;
    }

    public void releaseConnection(Connection connection){
        pool.put(connection,false);
    }
    void putConnectionToPool(Connection connection){
        pool.put(connection,false);
    }
}

package org.levelup.bank.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgreSQLConnectionsManager implements ConnectionManager {




    @Override
    public Connection openConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:postgresql://127.0.0.1:5432/bank_application",
                "postgres",
                "1qazXSW@"
        );
    }
}

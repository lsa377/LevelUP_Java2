package org.levelup.bank.jdbc;

import org.levelup.bank.jdbc.pool.ConnectionTime;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionManager {
    @ConnectionTime
    Connection openConnection() throws SQLException;
}

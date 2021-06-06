package org.levelup.bank.jdbc;

import lombok.AllArgsConstructor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@AllArgsConstructor
public class JdbsAccountRepository implements AccountRepository{

    private ConnectionManager cm;

    @Override
    public void createNewAccount(String account_number, Double amount, Long client_id) {
        try(Connection connection = cm.openConnection()){
            PreparedStatement stmt = connection.prepareStatement("insert into accounts (account_number,amount,client_id) values (?,?,?)");
            stmt.setString(1,account_number);
            stmt.setDouble(2,amount);
            stmt.setLong(3,client_id);
            int affectedRows = stmt.executeUpdate();
            System.out.println("Добавлено счетов - "+affectedRows);
        } catch (SQLException exc){
            System.out.println("Ошибка подключения к базе");
            exc.printStackTrace();
        }
    }

    @Override
    public void updateAccount(String account_number, Double amount) {
        try(Connection connection = cm.openConnection()){
            PreparedStatement stmt = connection.prepareStatement("update accounts set amount = ? where account_number = ?");
            stmt.setString(2,account_number);
            stmt.setDouble(1,amount);
            int affectedRows = stmt.executeUpdate();
            System.out.println("Обвнолено счетов - "+affectedRows);
        } catch (SQLException exc){
            System.out.println("Ошибка подключения к базе");
            exc.printStackTrace();
        }
    }



}

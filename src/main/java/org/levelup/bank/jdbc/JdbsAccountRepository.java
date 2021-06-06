package org.levelup.bank.jdbc;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.levelup.bank.domain.Account;
import org.levelup.bank.domain.ClientDebet;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

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
            System.out.println("Ошибка записи данных в базу");
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
            System.out.println("Обновлено счетов - "+affectedRows);
        } catch (SQLException exc){
            System.out.println("Ошибка записи данных в базу");
            exc.printStackTrace();
        }
    }

    @Override
    public Collection<ClientDebet> getClientSummaryAmount() {
        Collection<ClientDebet> debets = new ArrayList<>();
        try (Connection connection = cm.openConnection()) {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select client_id,sum (amount) as common_amount from accounts group by client_id");
            while (rs.next()) {
                debets.add(new ClientDebet(rs.getLong("client_id"), rs.getDouble("common_amount")));
            }
        } catch (SQLException exc){
            System.out.println("Ошибка чтения данных из базы");
            exc.printStackTrace();
        }
        return debets;
    }

    @Override
    public Collection<Account> getAccounts(){
        return getClientsCollection(executeQuery("select * from accounts"));
    }

    @Override
    public Collection<Account> getAccounts(Long client_id) {
        try (Connection connection = cm.openConnection()) {
            PreparedStatement stmt = connection.prepareStatement("select * from accounts where client_id = ?");
            stmt.setLong(1, client_id);
            ResultSet rs = stmt.executeQuery();
            return getClientsCollection(rs);
        } catch (SQLException exc) {
            System.out.println("Ошибка чтения данных из базы");
            exc.printStackTrace();
            return null;
        }
    }

    @Override
    public Collection<Account> getTopAmountByClient(){
        return getClientsCollection(executeQuery("select account_number,t1.amount,t1.client_id from (select max(amount) as amount,client_id from accounts group by client_id) as t1 join accounts on t1.amount = accounts.amount"));
    }

    private ResultSet executeQuery(String sql){
        try (Connection connection = cm.openConnection()) {
            Statement stmt = connection.createStatement();
            return stmt.executeQuery(sql);
        } catch (SQLException exc){
            System.out.println("Ошибка чтения данных из базы");
            exc.printStackTrace();
            return null;
        }
    }

    @SneakyThrows
    private Collection<Account> getClientsCollection(ResultSet rs){
        if(rs != null) {
            Collection<Account> accounts = new ArrayList<>();
            while (rs.next()) {
                accounts.add(new Account(rs.getString("account_number"), rs.getDouble("amount"), rs.getLong("client_id")));
            }
            return accounts;
        }
        else {
            return null;
        }
    }
}

package org.levelup.bank.jdbc;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.levelup.bank.domain.Client;
import org.levelup.bank.util.DateUtils;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

@AllArgsConstructor
public class JdbsClientRepository implements ClientRepository {

    private ConnectionManager cm;

    @Override
    public void createNewClient(String firstName, String lastName, String middleName, LocalDate birthday) {
        try(Connection connection = cm.openConnection()){
            PreparedStatement stmt = connection.prepareStatement("insert into clients (first_name,last_name,middle_name, birthdate) values (?,?,?,now())");
            stmt.setString(1,firstName);
            stmt.setString(2,lastName);
            stmt.setString(3,middleName);
            int affectedRows = stmt.executeUpdate();
            System.out.println("Добавлено клиентов - "+affectedRows);
        } catch (SQLException exc){
            System.out.println("Ошибка подключения к базе");
            exc.printStackTrace();
        }
    }

    public void printAllClients() {
        try (Connection connection = cm.openConnection()) {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from clients");
            while (rs.next()) {
                long clientId = rs.getLong(1);
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String middleName = rs.getString("middle_name");
                System.out.println(clientId + " " + firstName + " " + lastName + " " + middleName);
            }
        } catch (SQLException exc) {
            throw new RuntimeException(exc);
        }
    }

    @Override
    @SneakyThrows
    public Collection<Client> findClientsWhenBirthdayBetween(LocalDate begin, LocalDate end) {
        String sql = "select * from clients where birthday between ? and ?";
        try (Connection connection = cm.openConnection()) {

            Date beginDate = DateUtils.ofLocalDate(begin);
            Date endDate = DateUtils.ofLocalDate(end);

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDate(1, beginDate);
            statement.setDate(2, endDate);

            ResultSet rs = statement.executeQuery();
            return retrieveClientsFromResultSet(rs);
        }
    }

    private Collection<Client> retrieveClientsFromResultSet(ResultSet rs) throws SQLException {
        Collection<Client> clients = new ArrayList<>();
        while (rs.next()) {
            Client client = new Client(
                    rs.getLong("id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("middle_name"),
                    DateUtils.ofDate(rs.getDate("birthday"))
            );
            clients.add(client);
        }
        return clients;
    }
}
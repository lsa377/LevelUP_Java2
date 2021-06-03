package org.levelup.bank.jdbc;

public class BankApplication {
    public static void main(String[] args) {
        ConnectionManager cm = new PostgreSQLConnectionsManager();
        ClientRepository clientRepository = new JdbsClientRepository(cm);
        // clientRepository.createNewClient("Im","Kim","Chen",null);
       clientRepository.printAllClients();
    }
}

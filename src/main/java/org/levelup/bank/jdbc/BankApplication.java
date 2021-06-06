package org.levelup.bank.jdbc;

import org.levelup.bank.domain.Client;

import java.time.LocalDate;
import java.util.Collection;

public class BankApplication {
    public static void main(String[] args) {
        ConnectionManager cm = new PostgreSQLConnectionsManager();
        ClientRepository clientRepository = new JdbsClientRepository(cm);
        AccountRepository accountRepository = new JdbsAccountRepository(cm);
        // clientRepository.createNewClient("Im", "Kim Chen", null, null);
        // clientRepository.printAllClients();


        // Collection<Client> clients = clientRepository.findClientsWhenBirthdayBetween(
        //        LocalDate.of(1950, 1, 1),
        //        LocalDate.of(1980, 12, 31)
        // );

        //for (Client client : clients) {
        //    System.out.println(client.getClientId() + " " + client.getLastName() + " " + client.getBirthday());
        // }
        // accountRepository.createNewAccount("9646434321",134.89,(long)1);
        // accountRepository.updateAccount("1244",567.987);

    }
}

package org.levelup.bank.jdbc;

import org.levelup.bank.domain.Account;
import org.levelup.bank.domain.Client;
import org.levelup.bank.domain.ClientDebet;

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

        System.out.println("Сумма средств каждого из клиентов по счетам");
        Collection<ClientDebet> debets = accountRepository.getClientSummaryAmount();
        for(ClientDebet debet : debets){
         System.out.println("Клиент: "+debet.getClient_id()+", сумма средств на всех счетах: "+debet.getDebet());
        }
        System.out.println("Все счета");
        Collection<Account> allaccounts = accountRepository.getAccounts();
        for(Account account : allaccounts){
            System.out.println("Номер: "+account.getAccount_number()+", сумма: "+account.getAmount()+", ID клиента: "+account.getClient_id());
        }
        long clientid = 2;
        System.out.println("Все счета клиента "+clientid);
        Collection<Account> clientaccounts = accountRepository.getAccounts(clientid);
        for(Account account : clientaccounts){
            System.out.println("Номер: "+account.getAccount_number()+", сумма: "+account.getAmount()+", ID клиента: "+account.getClient_id());
        }
        System.out.println("Счета по каждому клиенту с наибольшим количеством средств");
        Collection<Account> topaccounts = accountRepository.getTopAmountByClient();
        for(Account account : topaccounts){
            System.out.println("Номер: "+account.getAccount_number()+", сумма: "+account.getAmount()+", ID клиента: "+account.getClient_id());
        }

    }
}

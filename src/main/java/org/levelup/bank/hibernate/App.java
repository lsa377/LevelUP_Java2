package org.levelup.bank.hibernate;

import org.hibernate.SessionFactory;
import org.levelup.bank.domain.AccountEntity;
import org.levelup.bank.domain.ClientEntity;
import org.levelup.bank.repository.AccountRepository;
import org.levelup.bank.repository.ClientRepository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;

public class App {
    public static void main(String[] args) {
        SessionFactory factory =  HibernateUtils.getFactory();
        // AccountRepository accountRepository = new AccountRepository(factory);
        // AccountEntity account = accountRepository.createAccount(1);
        // System.out.println(account.toString());
        // Collection<AccountEntity> accounts = accountRepository.loadClientAccounts(2);
        // for(AccountEntity account: accounts){
        //    System.out.println(account.toString());
        // }

        ClientRepository clientRepository = new ClientRepository(factory);

        ClientEntity newclient = clientRepository.createClient(1023,"Petr","Petrov",LocalDate.of(1983,10,1));
        System.out.println(newclient.toString());

        ClientEntity oldclient = clientRepository.editClient(1023,null,null,"Petrovich",null);
        System.out.println(oldclient.toString());

        Collection<ClientEntity> bornInPeriod = clientRepository.findClientsWhenBirthdayBetween(
                LocalDate.of(1950, 1, 1),
                LocalDate.of(1980, 12, 31)
        );
        for(ClientEntity client : bornInPeriod){
            System.out.println("Client - ID: "+client.getClientId()+", first name: "+client.getFirstName()+", middle name :"+client.getMiddleName()+", last name: "+client.getLastName()+", birth date: "+client.getBirthday());
        }


        clientRepository.printAllClients();
        clientRepository.deleteClient(1023);

        factory.close();
    }
}

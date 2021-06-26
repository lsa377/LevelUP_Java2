package org.levelup.bank.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.levelup.bank.domain.AccountEntity;
import org.levelup.bank.domain.ClientEntity;
import org.levelup.bank.domain.ManagerEntity;
import org.levelup.bank.repository.AccountRepository;
import org.levelup.bank.repository.ClientRepository;
import org.levelup.bank.repository.ManagerRepository;

import java.time.LocalDate;
import java.util.Collection;

public class App {
    public static void main(String[] args) {
        SessionFactory factory =  HibernateUtils.getFactory();
        AccountRepository accountRepository = new AccountRepository(factory);
        AccountEntity account = accountRepository.createAccount(1);
        Session s = factory.openSession();
        s.refresh(account);
        System.out.println(account.toString());
        Collection<AccountEntity> accounts = accountRepository.loadClientAccounts(2);
        for(AccountEntity account1: accounts){
            System.out.println(account1.toString());
        }
        ClientRepository clientRepository = new ClientRepository(factory);

       ClientEntity newclient = clientRepository.createClient("Ivan","Urgant","Andreevich",LocalDate.of(1977,10,1));
       System.out.println(newclient.toString());
       ClientEntity oldclient = clientRepository.editClient(1,null,null,"Abdurahman",null);
       System.out.println(oldclient.toString());
       Collection<ClientEntity> bornInPeriod = clientRepository.findClientsWhenBirthdayBetween(
                LocalDate.of(1950, 1, 1),
               LocalDate.of(1980, 12, 31)
       );
       for(ClientEntity client : bornInPeriod){
            System.out.println("Client - ID: "+client.getClientId()+", first name: "+client.getFirstName()+", middle name :"+client.getMiddleName()+", last name: "+client.getLastName()+", birth date: "+client.getBirthday());
       }


        clientRepository.printAllClients();
        clientRepository.deleteClient(7);
        clientRepository.printAllClients();

        // ManagerRepository managerRepository = new ManagerRepository(factory);
        // ManagerEntity manager1 = managerRepository.addManager("Hello","World");
        // ManagerEntity manager2 = managerRepository.addManager("Pier","Cardin");
        // ManagerEntity get = managerRepository.getById(manager1.getId());
        // ManagerEntity load = managerRepository.loadById(manager2.getId());

        // System.out.println(get);
        // Session s = factory.openSession();
        // s.refresh(get);
        // System.out.println(get.getFilials());
        // System.out.println(load);

        factory.close();
    }
}

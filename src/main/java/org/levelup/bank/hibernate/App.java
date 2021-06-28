package org.levelup.bank.hibernate;

import org.hibernate.SessionFactory;
import org.levelup.bank.domain.*;
import org.levelup.bank.repository.*;

import java.util.Collection;

public class App {
    public static void main(String[] args) {
        SessionFactory factory =  HibernateUtils.getFactory();
        AccountRepository accountRepository = new AccountRepository(factory);
        // AccountEntity account = accountRepository.createAccount(1);
        // Session s = factory.openSession();
        // s.refresh(account);
        // System.out.println(account.toString());
        //Collection<AccountEntity> accounts = accountRepository.loadClientAccounts(2);
        // for(AccountEntity account1: accounts){
        //    System.out.println(account1.toString());
        //}
        //ClientRepository clientRepository = new ClientRepository(factory);

       //ClientEntity newclient = clientRepository.createClient("Ivan","Urgant","Andreevich",LocalDate.of(1977,10,1));
       //System.out.println(newclient.toString());
       //ClientEntity oldclient = clientRepository.editClient(1,null,null,"Abdurahman",null);
       //System.out.println(oldclient.toString());
       //Collection<ClientEntity> bornInPeriod = clientRepository.findClientsWhenBirthdayBetween(
       //         LocalDate.of(1950, 1, 1),
       //        LocalDate.of(1980, 12, 31)
       // );
       //for(ClientEntity client : bornInPeriod){
       //     System.out.println("Client - ID: "+client.getClientId()+", first name: "+client.getFirstName()+", middle name :"+client.getMiddleName()+", last name: "+client.getLastName()+", birth date: "+client.getBirthday());
       //}


        // clientRepository.printAllClients();
        // clientRepository.deleteClient(7);
        // clientRepository.printAllClients();

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

        accountRepository.depositCash("fab1d516-91dc-40",3.0);
        OperationRepository operationRepository = new OperationRepository(factory);
        OperationEntity operation1 = operationRepository.createOperation("fab1d516-91dc-40","c3fd13f3-dfbe-48",1.0);
        System.out.println(operation1.toString());
        OperationEntity operation2 = operationRepository.rollbackOperation(operation1.getOperationId());
        System.out.println(operation2.toString());
        Collection<AccountEntity> accounts = accountRepository.loadClientAccounts();
        for(AccountEntity account : accounts){
            System.out.println(account.toString());
        }
        Collection<OperationEntity> operations = operationRepository.getAllOperations();
        for(OperationEntity operation : operations){
            System.out.println(operation.toString());
        }

        factory.close();
    }
}

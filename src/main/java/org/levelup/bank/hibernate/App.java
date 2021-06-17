package org.levelup.bank.hibernate;

import org.hibernate.SessionFactory;
import org.levelup.bank.domain.AccountEntity;
import org.levelup.bank.repository.AccountRepository;

import java.util.Collection;

public class App {
    public static void main(String[] args) {
        SessionFactory factory =  HibernateUtils.getFactory();
        AccountRepository accountRepository = new AccountRepository(factory);
        // AccountEntity account = accountRepository.createAccount(1);
        // System.out.println(account.toString());
        Collection<AccountEntity> accounts = accountRepository.loadClientAccounts(2);
        for(AccountEntity account: accounts){
            System.out.println(account.toString());
        }


        factory.close();
    }
}

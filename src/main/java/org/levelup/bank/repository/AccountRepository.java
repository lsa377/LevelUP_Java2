package org.levelup.bank.repository;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.levelup.bank.domain.AccountEntity;
import org.levelup.bank.domain.ClientEntity;

import java.util.Collection;
import java.util.UUID;

@RequiredArgsConstructor
public class AccountRepository {
    private final SessionFactory factory;

    public AccountEntity createAccount(long clientid){
        try(Session session = factory.openSession()){
           Transaction tx = session.beginTransaction();

           AccountEntity account = new AccountEntity();
           account.setClient(session.load(ClientEntity.class,clientid));
           account.setAmount(0.0d);
           account.setAccountNumber(UUID.randomUUID().toString().substring(0,16));

           session.save(account);

           tx.commit();
           return account;
        }
    }

    public void depositCash(String accountId,Double amount){
        try(Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            AccountEntity account = session.load(AccountEntity.class,accountId);
            account.setAmount(account.getAmount()+amount);
            tx.commit();
        }
    }

    public Collection<AccountEntity> loadClientAccounts(long clientId){
        try(Session session = factory.openSession()){
            return session.createQuery("from AccountEntity where client_Id = : clientId",AccountEntity.class)
                    .setParameter("clientId",clientId)
                    .getResultList();
        }
    }

    public Collection<AccountEntity> loadClientAccounts(){
        try(Session session = factory.openSession()){
            return session.createQuery("from AccountEntity",AccountEntity.class).getResultList();
        }
    }
}

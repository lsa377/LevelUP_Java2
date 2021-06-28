package org.levelup.bank.repository;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.levelup.bank.domain.AccountEntity;
import org.levelup.bank.domain.OperationEntity;

import java.util.ArrayList;
import java.util.Collection;

@RequiredArgsConstructor
public class OperationRepository {
    private final SessionFactory factory;

    public Collection<OperationEntity> getAllOperations(){
        try(Session session = factory.openSession()) {
        return session.createQuery("from OperationEntity",OperationEntity.class).getResultList();
        }
    }

    public OperationEntity createOperation(String fromAccount,String toAccount,Double amount){
        try(Session session = factory.openSession()){
            AccountEntity accountFrom = session.load(AccountEntity.class,fromAccount);
            if(accountFrom.getAmount() < amount) {
                System.out.println("Not enough money in the account " + fromAccount + ": requested " + amount + ", but " + accountFrom.getAmount() + " available.");
                return null;
            }else {
                Transaction tx = session.beginTransaction();
                OperationEntity operation = new OperationEntity();
                operation.setFromAccount(fromAccount);
                operation.setToAccount(toAccount);
                AccountEntity accountTo = session.load(AccountEntity.class, toAccount);
                accountFrom.setAmount(accountFrom.getAmount() - amount);
                accountTo.setAmount(accountTo.getAmount()+amount);
                operation.setAmount(amount);
                session.save(operation);
                tx.commit();
                return operation;
            }
        }
    }

    public OperationEntity rollbackOperation(Long operationId){
        try(Session session = factory.openSession()) {
            OperationEntity operation = session.load(OperationEntity.class,operationId);
            System.out.println("Operation revert requested: operation ID "+operation.getOperationId()+", amount of funds to return "+operation.getAmount()+", account to return money "+operation.getFromAccount()+", account from which money should be returned "+operation.getToAccount());
            AccountEntity accountTo = session.load(AccountEntity.class,operation.getToAccount());
            if(accountTo.getAmount() < operation.getAmount()){
                System.out.println("Operation can not be reverted: the amount to return is "+operation.getAmount()+", but the amount of the account is now "+accountTo.getAmount());
                return null;
            } else {
                Transaction tx = session.beginTransaction();
                OperationEntity backOperation = new OperationEntity();
                backOperation.setToAccount(operation.getFromAccount());
                backOperation.setFromAccount(operation.getToAccount());
                backOperation.setAmount(operation.getAmount());
                AccountEntity accountFrom = session.load(AccountEntity.class,operation.getFromAccount());
                accountTo.setAmount(accountTo.getAmount() - operation.getAmount());
                accountFrom.setAmount(accountFrom.getAmount() + operation.getAmount());
                session.save(backOperation);
                tx.commit();
                return backOperation;

            }
        }
    }
}

package org.levelup.bank.jdbc;

public interface AccountRepository {
    void createNewAccount(String account_number,Double amount,Long client_id);
    void updateAccount(String account_number, Double amount);
}

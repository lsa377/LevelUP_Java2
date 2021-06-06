package org.levelup.bank.jdbc;

import lombok.SneakyThrows;
import org.levelup.bank.domain.Account;
import org.levelup.bank.domain.ClientDebet;

import java.util.Collection;

public interface AccountRepository {
    void createNewAccount(String account_number,Double amount,Long client_id);
    void updateAccount(String account_number, Double amount);
    Collection<ClientDebet> getClientSummaryAmount();
    Collection<Account> getAccounts();
    Collection<Account> getAccounts(Long client_id);


    Collection<Account> getTopAmountByClient();
}

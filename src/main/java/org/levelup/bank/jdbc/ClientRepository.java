package org.levelup.bank.jdbc;

import org.levelup.bank.domain.Client;

import java.time.LocalDate;
import java.util.Collection;

public interface ClientRepository {

    void createNewClient(String firstName, String lastName, String middleName, LocalDate birthday);
    void printAllClients();
    Collection<Client> findCleintsWhenBirthdayBetween(LocalDate begin, LocalDate end);
}

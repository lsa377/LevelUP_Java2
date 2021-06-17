package org.levelup.bank.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@NoArgsConstructor
@Table(name = "accounts")
public class AccountEntity {

    @Id
    @Column(name = "account_number")
    private String accountNumber;
    private double amount;
    @Column(name = "client_id")
    private long clientId;
}

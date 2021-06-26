package org.levelup.bank.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@ToString(exclude = "client")
@Table(name = "accounts")
public class AccountEntity {

    @Id
    @Column(name = "account_number")
    private String accountNumber;
    @Column(columnDefinition = "NUMERIC(19,0)")
    private double amount;
    // @Column(name = "client_id")
    // private long clientId;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private ClientEntity client;
}

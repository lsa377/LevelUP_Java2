package org.levelup.bank.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.lang.annotation.Target;

@Data
@Entity
@NoArgsConstructor
@ToString(exclude = "client")
@Table(name="client_data")
public class ClientDataEntity {

    @Id
    @Column(name="client_id")
    private long clientId;
    @Column(name="passport_serial")
    private String passportSerial;
    @Column(name="passport_number")
    private String passportNumber;

    @OneToOne
    // @JoinColumn(name="client_id")
    @PrimaryKeyJoinColumn
    private ClientEntity client;

}

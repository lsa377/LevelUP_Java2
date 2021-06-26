package org.levelup.bank.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;

@Setter
@Getter
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "clients")
public class ClientEntity {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long clientId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "birthday")
    private LocalDate birthday;

    @OneToOne(mappedBy = "client",fetch=FetchType.EAGER)
    // @JoinColumn(name = "client_id")
    private ClientDataEntity clientData;

    @OneToMany(mappedBy = "client",fetch=FetchType.EAGER)
    private Collection<AccountEntity> accounts;

    public ClientEntity(long clientId, String firstName, String lastName, String middleName, LocalDate birthday) {
        this.clientId = clientId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.birthday = birthday;
    }
}

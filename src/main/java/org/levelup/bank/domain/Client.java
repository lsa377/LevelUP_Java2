package org.levelup.bank.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class Client {
    private long clientId;
    private String firstName;
    private String lastName;
    private String middleName;
    private LocalDate birthday;
}

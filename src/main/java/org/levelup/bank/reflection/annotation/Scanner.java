package org.levelup.bank.reflection.annotation;

import lombok.ToString;

@ToString
public class Scanner {
    private String ipAddress;
    @RandomString(isUpperCase = false)
    private String networkName;
    private boolean noConnection;
}

package org.levelup.bank.reflection.annotation;

import lombok.ToString;

@ToString
public class Server {
    private String ipAddress;
    private String serverName;
    private String domain;
    private int numberOfCores;
    @RandomString(length = 4,isUpperCase = true)
    private String identifier;

}

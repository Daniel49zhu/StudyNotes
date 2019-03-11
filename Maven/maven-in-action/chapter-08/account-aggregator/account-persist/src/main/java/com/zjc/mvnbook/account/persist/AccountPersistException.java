package com.zjc.mvnbook.account.persist;

public class AccountPersistException extends Exception {

    private static final long serialVersionUID = -769987654407584034L;

    public AccountPersistException(String message) {
        super(message);
    }

    public AccountPersistException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
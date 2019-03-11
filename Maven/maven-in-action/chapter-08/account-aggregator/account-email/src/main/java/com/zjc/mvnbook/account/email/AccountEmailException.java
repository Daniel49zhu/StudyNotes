package com.zjc.mvnbook.account.email;

public class AccountEmailException extends Exception{

    private static final long serialVersionUID = -960123402614003422L;

    public AccountEmailException(String message) {
        super(message);
    }

    public AccountEmailException(String message, Throwable cause) {
        super(message, cause);
    }
}

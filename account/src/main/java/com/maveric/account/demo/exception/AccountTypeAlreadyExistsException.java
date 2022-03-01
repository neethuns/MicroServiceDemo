package com.maveric.account.demo.exception;

public class AccountTypeAlreadyExistsException extends RuntimeException{
    public AccountTypeAlreadyExistsException(String message) {
        super(message);
    }
}

package com.maveric.account.demo.exception;

public class CustomerNotActiveException extends RuntimeException {
    public CustomerNotActiveException(String message) {
        super(message);
    }
}

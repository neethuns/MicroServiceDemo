package com.maveric.customer.demo.exception;



public class CustomerNotActiveException extends RuntimeException {
    public CustomerNotActiveException(String message) {
        super(message);
    }
}

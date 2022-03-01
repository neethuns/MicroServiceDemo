package com.maveric.customer.demo.exception;

public class CustomerNotFoundException extends RuntimeException{
public CustomerNotFoundException(String message){
    super(message);
}
}

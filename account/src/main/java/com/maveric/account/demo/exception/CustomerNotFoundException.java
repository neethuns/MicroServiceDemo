package com.maveric.account.demo.exception;

public class CustomerNotFoundException  extends RuntimeException{
    public CustomerNotFoundException (String message){
        super(message);
    }
}

package com.DDN.login.exception;

public class OrderNotFoundException extends IllegalArgumentException{
    public OrderNotFoundException(String msg){
        super(msg);
    }
}

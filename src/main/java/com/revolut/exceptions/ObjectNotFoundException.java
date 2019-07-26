package com.revolut.exceptions;

public class ObjectNotFoundException extends IllegalArgumentException {

    public ObjectNotFoundException(String message){
        super(message);
    }
}

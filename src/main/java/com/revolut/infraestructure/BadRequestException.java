package com.revolut.infraestructure;

import com.revolut.exceptions.MessageWrapperException;

import java.io.OutputStream;

public class BadRequestException extends MessageWrapperException {

    public BadRequestException(OutputStream message) throws Exception {
        super(message, "Bad request my friend");
    }

}

package com.revolut.exceptions;

import java.io.OutputStream;

public class MessageWrapperException extends IllegalArgumentException{

    public  MessageWrapperException (OutputStream out, Object outputMessage) throws Exception {
        new BaseMessageWrapper(out,outputMessage);
    }
}

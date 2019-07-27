package com.revolut.api;

import com.revolut.exceptions.BaseMessageWrapper;

import java.io.IOException;
import java.io.OutputStream;

public class MovementResponse extends BaseMessageWrapper {

    public MovementResponse(final OutputStream out, final Object o) throws IOException {
        super(out, o);
    }
}

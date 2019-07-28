package com.revolut.api;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.net.URI;

public class OperationsHandler implements HttpHandler {

    private static final String PATH_MOVE = "move";

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        final URI subUri = httpExchange.getRequestURI();
        final String subPath = subUri.getPath();
        if (subPath.endsWith(PATH_MOVE)) {
            new MovementHandler().handle(httpExchange);
        }
    }

}

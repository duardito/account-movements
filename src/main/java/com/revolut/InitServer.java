package com.revolut;

import com.revolut.api.OperationsHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InitServer {

    private final static  Logger LOGGER = Logger.getLogger(InitServer.class.getName());

    private static HttpServer server;

    public static void startServer() throws IOException {
        LOGGER.log(Level.INFO, "Server starting  ....");
        server = HttpServer.create(new InetSocketAddress(15000), 0);
        server.createContext("/", new OperationsHandler());
        server.start();
        LOGGER.log(Level.INFO, "Server started  ....");
        LOGGER.log(Level.INFO, "To test application just use this url : http://localhost:15000/operation/move ");
        LOGGER.log(Level.INFO, "you can use a postman collection included in resources folder");
    }

    public static void stopServer(){
        server.stop(1);
    }
}
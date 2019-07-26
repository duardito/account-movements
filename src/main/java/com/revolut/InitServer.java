package com.revolut;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class InitServer {

    private static HttpServer server;

    public static void startServer() throws IOException {
        server = HttpServer.create(new InetSocketAddress(15000), 0);
        server.createContext("/", new OperationsHandler());
        server.start();
    }

    public static void stopServer(){
        server.stop(1);
    }
}
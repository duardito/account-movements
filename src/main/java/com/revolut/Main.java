package com.revolut;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    private final static Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) throws IOException {
        LOGGER.log(Level.INFO, "Application starting ....");
        InitServer.startServer();
    }

}

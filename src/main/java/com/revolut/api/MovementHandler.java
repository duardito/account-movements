package com.revolut.api;

import com.revolut.MoveResponse;
import com.revolut.infraestructure.BadRequestException;
import com.revolut.repository.AccountRepository;
import com.revolut.repository.AccountRepositoryInMemory;
import com.revolut.service.*;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MovementHandler implements HttpHandler {

    private final static Logger LOGGER = Logger.getLogger(MovementHandler.class.getName());

    private final AccountRepository accountsRepository = new AccountRepositoryInMemory();
    private final GetAccountService getAccount = new GetAccount(accountsRepository);
    private final GetAccountToTransferService getAccountToTransferService = new GetAccountToTransfer(accountsRepository);
    private final MoveMoneyService moveMoneyService = new MoveMoney(getAccount, getAccountToTransferService);

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        Map<String, String> queryMap = null;
        try {
            queryMap = getParamsMap(httpExchange);
        } catch (Exception e) {
            //not needed to specify
        }
        RequestMovements request = buildRequest(queryMap);
        MoveResponse move = moveMoneyService.moveMoney(request.getIdFrom(), request.getIdTo(), request.getAmount());
        httpExchange.sendResponseHeaders(200, 0);
        new MovementResponse(httpExchange.getResponseBody(), move);
    }

    private RequestMovements buildRequest(Map<String, String> queryMap) {
        String idFrom = queryMap.get("idFrom");
        String idTo = queryMap.get("idTo");
        BigDecimal amount = new BigDecimal(queryMap.get("amount"));
        return new RequestMovements(idFrom, idTo, amount);
    }


    protected Map<String, String> getParamsMap(HttpExchange httpExchange) throws Exception {
        final InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), StandardCharsets.UTF_8);
        final BufferedReader br = new BufferedReader(isr);
        final String query = br.readLine();
        if (query == null) {
            LOGGER.log(Level.SEVERE, "Params not defined in request {} ", httpExchange.getResponseBody());
            httpExchange.sendResponseHeaders(400, 0);
            throw new BadRequestException(httpExchange.getResponseBody());
        }
        return queryToMap(query);
    }


    public static Map<String, String> queryToMap(final String query) {

        Map<String, String> result = new HashMap<>();
        for (String param : query.split("&")) {
            String[] pair = param.split("=");
            if (pair.length > 1) {
                result.put(pair[0], pair[1]);
            } else {
                result.put(pair[0], "");
            }
        }
        return result;
    }
}

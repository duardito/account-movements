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
import java.util.HashMap;
import java.util.Map;

public class MovementHandler implements HttpHandler {



    private AccountRepository accountsRepository = new AccountRepositoryInMemory();
    private GetAccountService getAccount = new GetAccount(accountsRepository);
    private GetAccountToTransferService getAccountToTransferService = new GetAccountToTransfer(accountsRepository);
    private MoveMoneyService moveMoneyService = new MoveMoney(getAccount, getAccountToTransferService);

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        Map<String, String> queryMap = null;
        try {
            queryMap = getParamsMap(httpExchange);
        } catch (Exception e) {

        }
        RequestMovements request = buildRequest(queryMap, httpExchange);
        MoveResponse move = moveMoneyService.moveMoney(request.getIdFrom(), request.getIdTo(), request.getAmount());
        httpExchange.sendResponseHeaders(200, 0);
        new MovementResponse(httpExchange.getResponseBody(), move);
    }

    private RequestMovements buildRequest(Map<String, String> queryMap, HttpExchange httpExchange) {

        String idFrom = queryMap.get("idFrom");
        String idTo = queryMap.get("idTo");
        BigDecimal amount = new BigDecimal(queryMap.get("amount"));
        return new RequestMovements(idFrom, idTo, amount);
    }


    protected Map<String, String> getParamsMap(HttpExchange httpExchange) throws Exception {
        final InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
        final BufferedReader br = new BufferedReader(isr);
        final String query = br.readLine();
        if (query == null) {
            httpExchange.sendResponseHeaders(400, 0);
            throw new BadRequestException(httpExchange.getResponseBody());
        }
        return queryToMap(query);
    }


    public static Map<String, String> queryToMap(final String query) {

        Map<String, String> result = new HashMap<String, String>();
        for (String param : query.split("&")) {
            String pair[] = param.split("=");
            if (pair.length > 1) {
                result.put(pair[0], pair[1]);
            } else {
                result.put(pair[0], "");
            }
        }
        return result;
    }
}

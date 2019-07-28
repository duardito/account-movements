package com.revolut.service;

import com.revolut.account.Account;
import com.revolut.account.AccountFrom;
import com.revolut.account.Movement;
import com.revolut.api.MoveResponse;

import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MoveMoney implements MoveMoneyService {

    private final static Logger LOGGER = Logger.getLogger(MoveMoney.class.getName());

    private final GetAccountService getAccountService;
    private final GetAccountToTransferService getAccountToTransferService;

    public MoveMoney(GetAccountService getAccountService, GetAccountToTransferService getAccountToTransferService) {
        this.getAccountService = getAccountService;
        this.getAccountToTransferService = getAccountToTransferService;
    }

    @Override
    public MoveResponse moveMoney(String from, String to, BigDecimal quantity) {
        final Account accountFrom = getAccountService.get(new AccountFrom(from));
        final Account accountTo = getAccountToTransferService.get(new AccountFrom(to));
        final Movement movement = new Movement().transfer(quantity, accountFrom, accountTo);
        getAccountService.update(movement.getDecreasedFrom(), movement.getUpdatedTo());
        LOGGER.log(Level.INFO, String.format("Transferred %s done from %s to %s ", String.valueOf(quantity), accountFrom.getId(), accountTo.getId()));
        return new MoveResponse(movement.getDecreasedFrom().getId(), movement.getUpdatedTo().getId(), movement.getDecreasedFrom().getAmount(), quantity);
    }
}

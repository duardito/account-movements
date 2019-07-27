package com.revolut.service;

import com.revolut.Account;
import com.revolut.AccountFrom;
import com.revolut.MoveResponse;
import com.revolut.amount.Movement;

import java.math.BigDecimal;

public class MoveMoney implements MoveMoneyService {

    private final GetAccountService getAccountService;
    private final GetAccountToTransferService getAccountToTransferService;

    public MoveMoney(GetAccountService getAccountService, GetAccountToTransferService getAccountToTransferService) {

        this.getAccountService = getAccountService;
        this.getAccountToTransferService = getAccountToTransferService;
    }

    @Override
    public MoveResponse moveMoney(String from, String to, BigDecimal quantity) {
        Account accountFrom = getAccountService.get(new AccountFrom(from));
        Account accountTo = getAccountToTransferService.get(new AccountFrom(to));
        final Movement movement = Movement.build(accountFrom, accountTo).money(quantity);
        getAccountService.update(movement.getDecreasedFrom(), movement.getUpdatedTo());

        return new MoveResponse(movement.getDecreasedFrom().getId(), movement.getUpdatedTo().getId(), movement.getDecreasedFrom().getAmount(), quantity);
    }
}

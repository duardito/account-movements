package com.revolut.service;

import com.revolut.Account;
import com.revolut.AccountFrom;
import com.revolut.AccountResponse;
import com.revolut.MoveResponse;
import com.revolut.amount.Movement;
import com.revolut.repository.AccountRepository;

import java.math.BigDecimal;

public class MoveMoney implements MoveMoneyService {


    private final GetAccountService getAccountService;

    public MoveMoney(GetAccountService getAccountService) {

        this.getAccountService = getAccountService;
    }

    @Override
    public MoveResponse moveMoney(String from, String to, BigDecimal quantity) {
        Account accountFrom = getAccountService.get(new AccountFrom(from));
        Account accountTo = getAccountService.get(new AccountFrom(to));
        final Movement movement = Movement.build(accountFrom, accountTo).money(quantity);
        getAccountService.update(movement.getDecreasedFrom(), movement.getUpdatedTo());
        return new MoveResponse(movement.getDecreasedFrom(), movement.getUpdatedTo());
    }
}

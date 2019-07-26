package com.revolut.service;

import com.revolut.Account;
import com.revolut.MoveResponse;
import com.revolut.amount.Movement;

import java.math.BigDecimal;

public class MoveMoney implements MoveMoneyService {

    @Override
    public MoveResponse moveMoney(Account from, Account to, BigDecimal quantity) {

        final Movement movement = Movement.build(from, to).money(quantity);
        return new MoveResponse(movement.getDecreasedFrom(), movement.getUpdatedTo());
    }
}

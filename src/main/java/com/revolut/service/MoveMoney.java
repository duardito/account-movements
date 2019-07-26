package com.revolut.service;

import com.revolut.Account;
import com.revolut.MoveResponse;
import com.revolut.amount.Move;

import java.math.BigDecimal;

public class MoveMoney implements MoveMoneyService {

    @Override
    public MoveResponse moveMoney(Account from, Account to, BigDecimal amount) {

        final Move move = Move.build(from, to);
        final Move movement = move.money(amount);
        return new MoveResponse(movement.getDecreasedFrom(), movement.getUpdatedTo());
    }
}

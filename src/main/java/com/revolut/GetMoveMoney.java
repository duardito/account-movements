package com.revolut;

import com.revolut.amount.Move;

import java.math.BigDecimal;

public class GetMoveMoney implements GetMoveMoneyService {

    @Override
    public MoveResponse moveMoney(Account from, Account to, BigDecimal amount) {

        Move move = Move.build(from, to);
        Move movement = move.money(amount);
        return new MoveResponse(movement.getDecreasedFrom(), movement.getUpdatedTo());
    }
}

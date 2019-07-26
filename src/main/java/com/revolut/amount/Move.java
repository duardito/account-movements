package com.revolut.amount;

import com.revolut.Account;

import java.math.BigDecimal;

public class Move {

    private Account from;
    private Account to;

    private Account updatedTo;
    private Account decreasedFrom;

    public static Move build(Account from, Account to){
        return new Move(from, to);
    }

    private Move(Account from, Account to) {
        this.from = from;
        this.to = to;
    }

    public Account getUpdatedTo() {
        return updatedTo;
    }

    public Account getDecreasedFrom() {
        return decreasedFrom;
    }

    public Move money(BigDecimal amount) {
        updatedTo = new Account(to.getId(), to.getAmount().add(amount));
        decreasedFrom = new Account(from.getId(), from.getAmount().subtract(amount));
        return this;
    }
}

package com.revolut.account;

import com.revolut.validator.Validate;

import java.math.BigDecimal;

public class Movement {

    private Account decreasedFrom;
    private Account updatedTo;

    public Movement() {
    }

    public Movement transfer(BigDecimal amount, Account from, Account to) {
        Validate.amountAccount(from, amount);
        updatedTo = new Account(to.getId(), to.getAmount().add(amount));
        decreasedFrom = new Account(from.getId(), from.getAmount().subtract(amount));
        return new Movement(decreasedFrom, updatedTo);
    }

    public Account getUpdatedTo() {
        return updatedTo;
    }

    public Account getDecreasedFrom() {
        return decreasedFrom;
    }

    private Movement(Account from, Account to) {
        this.decreasedFrom = from;
        this.updatedTo = to;
    }

}

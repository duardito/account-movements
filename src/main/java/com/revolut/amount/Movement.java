package com.revolut.amount;

import com.revolut.validator.Validate;

import java.math.BigDecimal;

public class Movement {

    private final Account from;
    private final Account to;

    private Account decreasedFrom;
    private Account updatedTo;

    public static Movement build(Account from, Account to)  {
        return new Movement(from, to);
    }

    public Movement money(BigDecimal amount) {
        Validate.amountFromAccount(from, amount);
        updatedTo = new Account(to.getId(), to.getAmount().add(amount));
        decreasedFrom = new Account(from.getId(), from.getAmount().subtract(amount));
        return this;
    }

    public Account getUpdatedTo() {
        return updatedTo;
    }

    public Account getDecreasedFrom() {
        return decreasedFrom;
    }

    private Movement(Account from, Account to) {
        this.from = from;
        this.to = to;
    }

}

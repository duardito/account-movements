package com.revolut;

import java.math.BigDecimal;

public class Account {

    private final String id;
    private final BigDecimal  amount;

    public Account(String id, BigDecimal  amount) {
        this.amount = amount;
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getId() {
        return id;
    }


}

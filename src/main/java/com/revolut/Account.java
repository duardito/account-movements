package com.revolut;

import java.math.BigDecimal;

public class Account {

    private String id;
    private BigDecimal  amount;

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

package com.revolut;

import java.math.BigDecimal;

public class AccountResponse {

    private String id;
    private BigDecimal amount;

    public AccountResponse(String id, BigDecimal amount) {
        this.id = id;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}

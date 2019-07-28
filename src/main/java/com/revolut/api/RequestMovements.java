package com.revolut.api;

import java.math.BigDecimal;

public class RequestMovements {

    private final String idFrom;
    private final String idTo;
    private final BigDecimal amount;

    public RequestMovements(String idFrom, String idTo, BigDecimal amount) {
        this.idFrom = idFrom;
        this.idTo = idTo;
        this.amount = amount;
    }

    public String getIdFrom() {
        return idFrom;
    }

    public String getIdTo() {
        return idTo;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}

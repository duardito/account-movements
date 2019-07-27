package com.revolut.api;

import java.math.BigDecimal;

public class RequestMovements {

    private String idFrom;
    private String idTo;
    private BigDecimal amount;

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

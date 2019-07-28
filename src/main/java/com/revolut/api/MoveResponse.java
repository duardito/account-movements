package com.revolut.api;

import java.math.BigDecimal;

public class MoveResponse {

    private final String idFrom;
    private final String idTo;
    private final BigDecimal amountRemaining;
    private final BigDecimal amountToMove;

    public MoveResponse(String idFrom, String idTo, BigDecimal amountRemaining, BigDecimal amountToMove) {
        this.idFrom = idFrom;
        this.idTo = idTo;
        this.amountRemaining = amountRemaining;
        this.amountToMove = amountToMove;
    }

    public String getIdFrom() {
        return idFrom;
    }

    public String getIdTo() {
        return idTo;
    }

    public BigDecimal getAmountRemaining() {
        return amountRemaining;
    }

    public BigDecimal getAmountToMove() {
        return amountToMove;
    }
}

package com.revolut;

import java.math.BigDecimal;

public class MoveResponse {

    private String idFrom;
    private String idTo;
    private BigDecimal amountRemaining;
    private BigDecimal amountToMove;

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

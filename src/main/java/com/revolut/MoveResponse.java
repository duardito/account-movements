package com.revolut;

public class MoveResponse {

    private Account from;
    private Account to;

    public MoveResponse(Account from, Account to) {
        this.from = from;
        this.to = to;
    }

    public Account getFrom() {
        return from;
    }

    public Account getTo() {
        return to;
    }

}

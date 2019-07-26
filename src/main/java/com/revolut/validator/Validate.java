package com.revolut.validator;

import com.revolut.Account;
import com.revolut.exceptions.InvalidAmountException;

import java.math.BigDecimal;

public class Validate {

    public static void amountFromAccount(Account from, BigDecimal amount) {
        if (from.getAmount().compareTo(amount) < 0) {
            throw new InvalidAmountException(String.format("Invalid amount {}", amount));
        }
    }

}

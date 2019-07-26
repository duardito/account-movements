package com.revolut.validator;

import com.revolut.Account;
import com.revolut.exceptions.InvalidAmountException;

import java.math.BigDecimal;

public class Validate {

    public static void amountFromAccount(Account from, BigDecimal amount) {
        zeroAmount(amount);
        negativeAmount(from, amount);
    }

    private static void zeroAmount(BigDecimal amount) {
        if (amount.equals(new BigDecimal(0))) {
            throw new InvalidAmountException(String.format("Invalid zero amount %b", amount));
        }
    }

    private static void negativeAmount(Account from, BigDecimal amount) {
        if (from.getAmount().compareTo(amount) < 0) {
            throw new InvalidAmountException(String.format("%s : %g","Amount to transfer bigger than current", amount));
        }
    }

}

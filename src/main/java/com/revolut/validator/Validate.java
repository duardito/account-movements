package com.revolut.validator;

import com.revolut.amount.Account;
import com.revolut.exceptions.InvalidAmountException;

import java.math.BigDecimal;

public class Validate {

    public static void amountFromAccount(Account from, BigDecimal amount) {
        zeroAmount(amount);
        negativeCurrentAmount(from);
        amountToTransferBiggerThanCurrent(from, amount);

    }

    private static void zeroAmount(BigDecimal amount) {
        if (amount.equals(new BigDecimal(0))) {
            throw new InvalidAmountException(String.format("Invalid zero amount %b", amount));
        }
    }

    private static void amountToTransferBiggerThanCurrent(Account from, BigDecimal amount) {
        if (from.getAmount().compareTo(amount) < 0) {
            throw new InvalidAmountException(String.format("%s : %g", "Amount to transfer bigger than current", amount));
        }
    }

    private static void negativeCurrentAmount(Account from) {
        if (from.getAmount().compareTo(new BigDecimal(0)) < 0) {
            throw new InvalidAmountException(String.format("%s : %g", "Current account with negative amount", from.getAmount()));
        }
    }

}

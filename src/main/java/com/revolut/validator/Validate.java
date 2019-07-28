package com.revolut.validator;

import com.revolut.account.Account;
import com.revolut.exceptions.InvalidAmountException;

import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Validate {

    private final static Logger LOGGER = Logger.getLogger(Validate.class.getName());

    public static void amountAccount(Account from, BigDecimal amount) {
        zero(amount);
        negative(amount);
        negativeAmount(from);
        amountToTransferBiggerThanCurrent(from, amount);
    }

    private static void zero(BigDecimal amount) {
        if (amount.equals(new BigDecimal(0))) {
            String invalid = "Invalid zero amount";
            LOGGER.log(Level.SEVERE, invalid);
            throw new InvalidAmountException(invalid);
        }
    }

    private static void amountToTransferBiggerThanCurrent(Account account, BigDecimal amount) {
        if (account.getAmount().compareTo(amount) < 0) {
            String invalid = String.format("%s : %g", "Amount to transfer bigger than current", amount);
            LOGGER.log(Level.SEVERE, invalid);
            throw new InvalidAmountException(invalid);
        }
    }

    private static void negativeAmount(Account from) {
        if (from.getAmount().compareTo(new BigDecimal(0)) < 0) {
            String invalid = String.format("%s : %g", "Current account with negative amount", from.getAmount());
            LOGGER.log(Level.SEVERE, invalid);
            throw new InvalidAmountException(invalid);
        }
    }

    private static void negative(BigDecimal amount) {
        if (amount.compareTo(new BigDecimal(0)) < 0) {
            String invalid = String.format("%s : %g", "Negative amount", amount);
            LOGGER.log(Level.SEVERE, invalid);
            throw new InvalidAmountException(invalid);
        }
    }

}

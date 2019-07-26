package com.revolut.service;

import com.revolut.Account;
import com.revolut.MoveResponse;

import java.math.BigDecimal;

public interface MoveMoneyService {

     MoveResponse moveMoney(Account from, Account to, BigDecimal amount);
}

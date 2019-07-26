package com.revolut;

import java.math.BigDecimal;

public interface GetMoveMoneyService {

     MoveResponse moveMoney(Account from, Account to, BigDecimal amount);
}

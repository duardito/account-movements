package com.revolut.service;

import com.revolut.api.MoveResponse;

import java.math.BigDecimal;

public interface MoveMoneyService {

     MoveResponse moveMoney(String from, String to, BigDecimal amount);
}

package com.revolut.service;

import com.revolut.MoveResponse;

import java.math.BigDecimal;

public interface MoveMoneyService {

     MoveResponse moveMoney(String from, String to, BigDecimal amount);
}

package com.revolut.service;

import com.revolut.amount.Account;
import com.revolut.amount.AccountFrom;

public interface GetAccountService {

    Account get(AccountFrom account);

    void update(Account from, Account to);
}

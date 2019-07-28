package com.revolut.service;

import com.revolut.Account;
import com.revolut.AccountFrom;

public interface GetAccountService {

    Account get(AccountFrom account);

    void update(Account from, Account to);
}

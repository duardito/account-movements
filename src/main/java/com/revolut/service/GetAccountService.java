package com.revolut.service;

import com.revolut.account.Account;
import com.revolut.account.AccountFrom;

public interface GetAccountService {

    Account get(AccountFrom account);

    void update(Account from, Account to);
}

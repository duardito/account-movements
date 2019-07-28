package com.revolut.repository;

import com.revolut.account.Account;

public interface AccountRepository {

    Account getBy(String id);

    void update(Account from, Account to);
}

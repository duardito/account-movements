package com.revolut.repository;

import com.revolut.Account;

import java.util.List;

public interface AccountRepository {

    List<Account> getAccounts();

    Account getBy(String id);

    Account update(Account from, Account to);
}

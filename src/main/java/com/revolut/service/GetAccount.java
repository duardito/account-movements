package com.revolut.service;

import com.revolut.Account;
import com.revolut.AccountFrom;
import com.revolut.AccountResponse;
import com.revolut.repository.AccountRepository;

public class GetAccount implements GetAccountService {

    private final AccountRepository accountRepository;

    public GetAccount(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountResponse get(AccountFrom accountFrom) {

        final Account account = accountRepository.getBy(accountFrom.getId());
        return new AccountResponse(account.getId(), account.getAmount());
    }

}

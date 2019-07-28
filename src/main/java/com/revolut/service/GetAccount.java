package com.revolut.service;

import com.revolut.Account;
import com.revolut.AccountFrom;
import com.revolut.repository.AccountRepository;

public class GetAccount implements GetAccountService {

    private final AccountRepository accountRepository;

    public GetAccount(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account get(AccountFrom accountFrom) {
        return accountRepository.getBy(accountFrom.getId());
    }

    @Override
    public void update(Account from,Account to){
        accountRepository.update(from,to);
    }

}

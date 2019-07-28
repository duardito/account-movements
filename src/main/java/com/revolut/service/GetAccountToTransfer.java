package com.revolut.service;

import com.revolut.account.Account;
import com.revolut.account.AccountFrom;
import com.revolut.repository.AccountRepository;

/**
 * class to simulate an external service, it can be used to transfer transfer to an external account or account from same bank
 * using same repository accounts just for simulate data, we should replace by external service or call to same bank
 */
public class GetAccountToTransfer implements GetAccountToTransferService {

    private final AccountRepository accountRepository;

    public GetAccountToTransfer(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account get(AccountFrom account) {
        return accountRepository.getBy(account.getId());
    }
}

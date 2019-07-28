package com.revolut.service;

import com.revolut.account.Account;
import com.revolut.account.AccountFrom;

public interface GetAccountToTransferService {

    Account get(AccountFrom account);
}

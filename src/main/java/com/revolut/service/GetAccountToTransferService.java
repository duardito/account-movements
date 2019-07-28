package com.revolut.service;

import com.revolut.amount.Account;
import com.revolut.amount.AccountFrom;

public interface GetAccountToTransferService {

    Account get(AccountFrom account);
}

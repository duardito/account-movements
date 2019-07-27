package com.revolut.service;

import com.revolut.Account;
import com.revolut.AccountFrom;

public interface GetAccountToTransferService {

    Account get(AccountFrom account);
}

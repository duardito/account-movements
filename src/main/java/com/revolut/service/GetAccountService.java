package com.revolut.service;

import com.revolut.AccountFrom;
import com.revolut.AccountResponse;

public interface GetAccountService {

    AccountResponse get(AccountFrom account);
}

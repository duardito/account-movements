package com.revolut.service;

import com.revolut.Account;
import com.revolut.AccountFrom;
import com.revolut.exceptions.ObjectNotFoundException;
import com.revolut.repository.AccountRepository;
import com.revolut.repository.AccountRepositoryInMemory;
import com.revolut.service.GetAccount;
import com.revolut.service.GetAccountService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class AccountsTest {

    private GetAccountService getAccountService;

    @Before
    public void setup() {
        getAccountService = new GetAccount(new AccountRepositoryInMemory());
    }

    @Test
    public void should_get_an_account() {

        String id = "asdfr124-323-ddf33";

        AccountFrom account = new AccountFrom(id);
        Account accountResponse = getAccountService.get(account);
        Assert.assertEquals(id, accountResponse.getId());
        Assert.assertEquals(new BigDecimal(1000.50), accountResponse.getAmount());
    }

    @Test(expected = ObjectNotFoundException.class)
    public void should_not_get_an_account_not_exists() {

        String id = "asdfr124-aaa-ddf33";
        AccountFrom account = new AccountFrom(id);
        getAccountService.get(account);
    }
}

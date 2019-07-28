package com.revolut.service;

import com.revolut.amount.Account;
import com.revolut.amount.AccountFrom;
import com.revolut.exceptions.ObjectNotFoundException;
import com.revolut.repository.AccountRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

public class AccountsTest {

    private GetAccountService getAccountService;

    @Mock
    private AccountRepository accountRepository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        getAccountService = new GetAccount(accountRepository);
    }

    @Test
    public void should_get_an_account() {

        String id = "asdfr124-323-ddf33";

        AccountFrom account = new AccountFrom(id);
        Account accountMockedFrom = new Account(id, new BigDecimal(1000.50));
        Mockito.when(accountRepository.getBy(account.getId())).thenReturn(accountMockedFrom);

        Account accountResponse = getAccountService.get(account);
        Assert.assertEquals(id, accountResponse.getId());
        Assert.assertEquals(new BigDecimal(1000.50), accountResponse.getAmount());
    }

    @Test(expected = ObjectNotFoundException.class)
    public void should_not_get_an_account_not_exists() {

        String id = "not-exists";

        AccountFrom account = new AccountFrom(id);
        Mockito.when(accountRepository.getBy(account.getId())).thenThrow(ObjectNotFoundException.class);

        getAccountService.get(account);
    }
}

package com.revolut;

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
        getAccountService = new GetAccount();
    }

    @Test
    public void should_get_an_account() {
        String id = "asdfr124-323-ddf33";
        AccountFrom account = new AccountFrom(id);
        AccountResponse accountResponse = getAccountService.get(account);
        Assert.assertEquals(id, accountResponse.getId());
        Assert.assertEquals(new BigDecimal(1000.50), accountResponse.getAmount());
    }
}

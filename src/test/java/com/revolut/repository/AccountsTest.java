package com.revolut.repository;


import com.revolut.Account;
import com.revolut.exceptions.ObjectNotFoundException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class AccountsTest {

    private AccountRepository accountRepository;

    @Before
    public void setup() {
        accountRepository = new AccountRepositoryInMemory();
    }

    @Test(expected = ObjectNotFoundException.class)
    public void should_not_get_existing_account() {
        accountRepository.getBy("wqwer");
    }

    @Test
    public void should_get_existing_account() {
        String id = "dfgthrhh-968-dyh53";
        Account account = accountRepository.getBy(id);
        Assert.assertEquals(new BigDecimal(-200), account.getAmount());
        Assert.assertEquals("dfgthrhh-968-dyh53", id);
    }

    @Test
    public void should_update_amount_from_accounts() {

        String idFrom = "123-928-dki53";
        Account accFrom = accountRepository.getBy(idFrom);
        Assert.assertEquals(new BigDecimal(1200), accFrom.getAmount());

        String idTo = "456-968-dyh53";
        Account accTo = accountRepository.getBy(idTo);
        Assert.assertEquals(new BigDecimal(-50), accTo.getAmount());

        Account from = new Account(idFrom, new BigDecimal(1100));
        Account to = new Account(idTo, new BigDecimal(50));
        accountRepository.update(from, to);

        accFrom = accountRepository.getBy(idFrom);
        Assert.assertEquals(new BigDecimal(1100), accFrom.getAmount());
        accTo = accountRepository.getBy(idTo);
        Assert.assertEquals(new BigDecimal(50), accTo.getAmount());


    }
}

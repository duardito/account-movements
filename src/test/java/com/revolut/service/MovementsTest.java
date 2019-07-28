package com.revolut.service;

import com.revolut.amount.Account;
import com.revolut.api.MoveResponse;
import com.revolut.exceptions.InvalidAmountException;
import com.revolut.repository.AccountRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;


public class MovementsTest {

    private MoveMoneyService moveMoneyService;

    @Mock
    private AccountRepository accountRepository;


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        moveMoneyService = new MoveMoney(new GetAccount(accountRepository), new GetAccountToTransfer(accountRepository));
    }

    @Test(expected = InvalidAmountException.class)
    public void should_not_move_money_between_accounts_negative_amount() {

        String idFrom = "dfgthrhh-968-dyh53";
        String idTo = "asdfr1uj-456-ggf33";

        Account accountMockedFrom = new Account(idFrom, new BigDecimal(-200));

        Mockito.when(accountRepository.getBy(idFrom)).thenReturn(accountMockedFrom);
        Account account = accountRepository.getBy(idFrom);
        Assert.assertEquals(new BigDecimal(-200), account.getAmount());

        moveMoneyService.moveMoney(idFrom, idTo, new BigDecimal(200));
    }

    @Test
    public void should_move_money_between_accounts() {

        String idFrom = "547h56y5hgt";
        String idTo   = "etryertyrte";

        Account accountMockedFrom = new Account(idFrom, new BigDecimal(1000.5));
        Account accountMockedTo = new Account(idTo, new BigDecimal(1000.5));

        Mockito.when(accountRepository.getBy(idFrom)).thenReturn(accountMockedFrom);
        Mockito.when(accountRepository.getBy(idTo)).thenReturn(accountMockedTo);

        Account account = accountRepository.getBy(idFrom);
        Assert.assertEquals(new BigDecimal(1000.5), account.getAmount());

        MoveResponse moveResponse = moveMoneyService.moveMoney(idFrom, idTo, new BigDecimal(1000));

        Assert.assertEquals(new BigDecimal(0.5), moveResponse.getAmountRemaining());
        Assert.assertEquals(idFrom, moveResponse.getIdFrom());

        Assert.assertEquals(new BigDecimal(1000), moveResponse.getAmountToMove());
        Assert.assertEquals(idTo, moveResponse.getIdTo());
    }

    @Test(expected = InvalidAmountException.class)
    public void should_not_move_money_between_accounts_not_enough_amount() {

        String idFrom = "asdfr124-323-ddf33";
        String idTo = "asdfr1uj-456-ggf33";

        Account accountMockedFrom = new Account(idFrom, new BigDecimal(1000.5));
        Account accountMockedTo = new Account(idTo, new BigDecimal(1000.5));

        Mockito.when(accountRepository.getBy(idFrom)).thenReturn(accountMockedFrom);
        Mockito.when(accountRepository.getBy(idTo)).thenReturn(accountMockedTo);

        moveMoneyService.moveMoney(idFrom, idTo, new BigDecimal(5200));
    }

    @Test(expected = InvalidAmountException.class)
    public void should_not_move_money_between_accounts_zero_amount() {

        String idFrom = "asdfr124-323-ddf33";
        String idTo = "asdfr1uj-456-ggf33";
        moveMoneyService.moveMoney(idFrom, idTo, new BigDecimal(0));

    }
}

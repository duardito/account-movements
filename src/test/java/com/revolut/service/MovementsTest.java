package com.revolut.service;

import com.revolut.Account;
import com.revolut.MoveResponse;
import com.revolut.exceptions.InvalidAmountException;
import com.revolut.repository.AccountRepository;
import com.revolut.repository.AccountRepositoryInMemory;
import com.revolut.service.GetAccount;
import com.revolut.service.GetAccountToTransfer;
import com.revolut.service.MoveMoney;
import com.revolut.service.MoveMoneyService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.math.BigDecimal;


public class MovementsTest {

    private MoveMoneyService moveMoneyService;

    private AccountRepository accountRepository;

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Before
    public void setup() {
        accountRepository = new AccountRepositoryInMemory();
        moveMoneyService = new MoveMoney(new GetAccount(accountRepository), new GetAccountToTransfer(accountRepository));
    }

    @Test(expected = InvalidAmountException.class)
    public void should_not_move_money_between_accounts_negative_amount() {

        String idFrom = "dfgthrhh-968-dyh53";
        String idTo = "asdfr1uj-456-ggf33";

        Account account = accountRepository.getBy(idFrom);
        Assert.assertEquals(new BigDecimal(-200), account.getAmount());

        moveMoneyService.moveMoney(idFrom, idTo, new BigDecimal(200));
    }

    @Test
    public void should_move_money_between_accounts() {

        String idFrom = "asdfr124-323-ddf33";
        String idTo = "asdfr1uj-456-ggf33";

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

        moveMoneyService.moveMoney(idFrom, idTo, new BigDecimal(5200));
    }

    @Test(expected = InvalidAmountException.class)
    public void should_not_move_money_between_accounts_zero_amount() {

        String idFrom = "asdfr124-323-ddf33";
        String idTo = "asdfr1uj-456-ggf33";
        moveMoneyService.moveMoney(idFrom, idTo, new BigDecimal(0));

    }
}

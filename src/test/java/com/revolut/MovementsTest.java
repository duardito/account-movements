package com.revolut;

import com.revolut.exceptions.InvalidAmountException;
import com.revolut.repository.AccountRepositoryInMemory;
import com.revolut.service.GetAccount;
import com.revolut.service.GetAccountService;
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


    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Before
    public void setup() {

        moveMoneyService = new MoveMoney( new GetAccount(new AccountRepositoryInMemory()));
    }

    @Test
    public void should_move_money_between_accounts() {

        String idFrom = "asdfr124-323-ddf33";
        String idTo = "asdfr1uj-456-ggf33";

        MoveResponse moveResponse = moveMoneyService.moveMoney(idFrom, idTo, new BigDecimal(1000));

        Assert.assertEquals(new BigDecimal(0.5), moveResponse.getFrom().getAmount());
        Assert.assertEquals(idFrom, moveResponse.getFrom().getId());

        Assert.assertEquals(new BigDecimal(11500), moveResponse.getTo().getAmount());
        Assert.assertEquals(idTo, moveResponse.getTo().getId());
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

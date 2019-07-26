package com.revolut;

import com.revolut.exceptions.InvalidAmountException;
import com.revolut.service.GetAccount;
import com.revolut.service.GetAccountService;
import com.revolut.service.MoveMoney;
import com.revolut.service.MoveMoneyService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class MovementsTest {

    private MoveMoneyService moveMoneyService;

    @Before
    public void setup() {
        moveMoneyService = new MoveMoney();
    }

    @Test
    public void should_move_money_between_accounts() {

        String idFrom = "asdfr124-323-ddf33";
        Account from = new Account(idFrom, new BigDecimal(1200));
        String idTo = "atdd2124-6ht-vfde4";
        Account to = new Account(idTo, new BigDecimal(0));
        MoveResponse moveResponse = moveMoneyService.moveMoney(from, to, new BigDecimal(1200));

        Assert.assertEquals(new BigDecimal(0), moveResponse.getFrom().getAmount());
        Assert.assertEquals(idFrom, moveResponse.getFrom().getId());

        Assert.assertEquals(new BigDecimal(1200), moveResponse.getTo().getAmount());
        Assert.assertEquals(idTo, moveResponse.getTo().getId());
    }

    @Test(expected = InvalidAmountException.class)
    public void should_not_move_money_between_accounts_not_enough_amount() {

        String idFrom = "asdfr124-323-ddf33";
        Account from = new Account(idFrom, new BigDecimal(1200));
        String idTo = "atdd2124-6ht-vfde4";
        Account to = new Account(idTo, new BigDecimal(0));
        moveMoneyService.moveMoney(from, to, new BigDecimal(5200));
    }

}

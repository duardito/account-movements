package com.revolut;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class MovementsTest {

    private GetMoveMoneyService getMoveMoneyService;

    @Before
    public void setup() {
        getMoveMoneyService = new GetMoveMoney();
    }

    @Test
    public void should_move_money_between_accounts() {

        String idFrom = "asdfr124-323-ddf33";
        Account from = new Account(idFrom, new BigDecimal(1200));
        String idTo = "asdfr124-323-ddf33";
        Account to = new Account(idTo, new BigDecimal(0));
        MoveResponse moveResponse = getMoveMoneyService.moveMoney(from, to , new BigDecimal(1200));

        Assert.assertEquals(new BigDecimal(0), moveResponse.getFrom().getAmount());
        Assert.assertEquals(idFrom, moveResponse.getFrom().getId());

        Assert.assertEquals(new BigDecimal(1200), moveResponse.getTo().getAmount());
        Assert.assertEquals(idTo, moveResponse.getTo().getId());
    }


}

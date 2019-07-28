package com.revolut.integration;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.revolut.InitServer;
import com.revolut.infraestructure.BadRequestException;
import org.junit.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class MovementsTest extends BaseTest {

    @BeforeClass
    public static void setup() throws IOException {
        InitServer.startServer();
    }

    @AfterClass
    public static void end() {
        InitServer.stopServer();
    }

    @Test(expected = BadRequestException.class)
    public void should_fail_request_wrong_type_to_amount() throws Exception {

        final String idTo = "asdfr124-323-ddf33";

        Map<String, Object> map = new HashMap<>();
        map.put("idFrom", "32452345");
        map.put("idTo", idTo);
        map.put("amount", "ada");
        getJsonResponsePut(OPERATION_MOVE, map);
    }

    @Test(expected = BadRequestException.class)
    public void should_fail_request_empty_amount() throws Exception {

        final String idTo = "asdfr124-323-ddf33";

        Map<String, Object> map = new HashMap<>();
        map.put("idFrom", "");
        map.put("idTo", idTo);
        map.put("amount", "");
        getJsonResponsePut(OPERATION_MOVE, map);
    }

    @Test(expected = BadRequestException.class)
    public void should_fail_request_empty_value() throws Exception {

        final String idTo = "asdfr124-323-ddf33";

        Map<String, Object> map = new HashMap<>();
        map.put("idFrom", "");
        map.put("idTo", idTo);
        map.put("amount", new BigDecimal(120));
        getJsonResponsePut(OPERATION_MOVE, map);
    }

    @Test(expected = BadRequestException.class)
    public void should_fail_request_field_not_sent() throws Exception {

        final String idTo = "asdfr124-323-ddf33";

        Map<String, Object> map = new HashMap<>();
        map.put("idTo", idTo);
        map.put("amount", new BigDecimal(120));
        getJsonResponsePut(OPERATION_MOVE, map);
    }
    /*
    accounts.add(new Account("asdfr124-323-ddf33", new BigDecimal(1000.50)));
        accounts.add(new Account("asdfr1uj-456-ggf33", new BigDecimal(10500)));

        accounts.add(new Account("asdfrooo-874-ddf33", new BigDecimal(31000.45)));
        accounts.add(new Account("asdfrkkk-908-dtf53", new BigDecimal(15500)));

        accounts.add(new Account("gfdhgthg-928-dki53", new BigDecimal(0)));
        accounts.add(new Account("dfgthrhh-968-dyh53", new BigDecimal(-200)));
     */

    @Test
    public void should_move_money_between_accounts() throws Exception {

        String idFrom = "asdfrooo-874-ddf33";
        String idTo = "asdfrkkk-908-dtf53";
        int amount = 250;

        Map<String, Object> map = new HashMap<>();
        map.put("idFrom", idFrom);
        map.put("idTo", idTo);
        map.put("amount", new BigDecimal(amount));
        JsonObject response = getJsonResponsePut(OPERATION_MOVE, map);

        JsonElement idFromResponse = response.get("idFrom");
        JsonElement idToResponse = response.get("idTo");
        JsonElement amountRemaining = response.get("amountRemaining");
        JsonElement amountToMove = response.get("amountToMove");

        Assert.assertEquals(idFromResponse.getAsString(), idFrom);
        Assert.assertEquals(idToResponse.getAsString(), idTo);
        Assert.assertEquals(new BigDecimal(amountRemaining.getAsString()), new BigDecimal(30750.5));
        Assert.assertEquals(new BigDecimal(amountToMove.getAsString()), new BigDecimal(amount));
    }

    @Test
    public void should_move_money_twice_between_same_accounts() throws Exception {

        final String idFrom = "asdfr1uj-456-ggf33";
        final String idTo = "asdfr124-323-ddf33";

        firstOperation(idFrom, idTo);
        secondOperation(idFrom, idTo);
    }

    private void firstOperation(String idFrom, String idTo) throws Exception {

        int amount = 120;

        Map<String, Object> map = new HashMap<>();
        map.put("idFrom", idFrom);
        map.put("idTo", idTo);
        map.put("amount", new BigDecimal(amount));
        JsonObject response = getJsonResponsePut(OPERATION_MOVE, map);

        JsonElement idFromResponse = response.get("idFrom");
        JsonElement idToResponse = response.get("idTo");
        JsonElement amountRemaining = response.get("amountRemaining");
        JsonElement amountToMove = response.get("amountToMove");

        Assert.assertEquals(idFromResponse.getAsString(), idFrom);
        Assert.assertEquals(idToResponse.getAsString(), idTo);
        Assert.assertEquals(new BigDecimal(amountRemaining.getAsString()), new BigDecimal(10380));
        Assert.assertEquals(new BigDecimal(amountToMove.getAsString()), new BigDecimal(amount));
    }

    private void secondOperation(String idFrom, String idTo) throws Exception {

        int amount = 1200;

        Map<String, Object> map = new HashMap<>();
        map.put("idFrom", idFrom);
        map.put("idTo", idTo);
        map.put("amount", new BigDecimal(amount));
        JsonObject response = getJsonResponsePut(OPERATION_MOVE, map);

        JsonElement idFromResponse = response.get("idFrom");
        JsonElement idToResponse = response.get("idTo");
        JsonElement amountRemaining = response.get("amountRemaining");
        JsonElement amountToMove = response.get("amountToMove");

        Assert.assertEquals(idFromResponse.getAsString(), idFrom);
        Assert.assertEquals(idToResponse.getAsString(), idTo);
        Assert.assertEquals(new BigDecimal(amountRemaining.getAsString()), new BigDecimal(9180));
        Assert.assertEquals(new BigDecimal(amountToMove.getAsString()), new BigDecimal(amount));
    }
}

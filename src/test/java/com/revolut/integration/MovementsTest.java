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
        map.put("idFrom", "");
        map.put("idTo", idTo);
        map.put("amount", "ada");
        getJsonResponsePut("http://localhost:15000/operation/move", map);
    }

    @Test(expected = BadRequestException.class)
    public void should_fail_request_empty_amount() throws Exception {

        final String idTo = "asdfr124-323-ddf33";

        Map<String, Object> map = new HashMap<>();
        map.put("idFrom", "");
        map.put("idTo", idTo);
        map.put("amount", "");
        getJsonResponsePut("http://localhost:15000/operation/move", map);
    }

    @Test(expected = BadRequestException.class)
    public void should_fail_request_empty_value() throws Exception {

        final String idTo = "asdfr124-323-ddf33";

        Map<String, Object> map = new HashMap<>();
        map.put("idFrom", "");
        map.put("idTo", idTo);
        map.put("amount", new BigDecimal(120));
        getJsonResponsePut("http://localhost:15000/operation/move", map);
    }

    @Test(expected = BadRequestException.class)
    public void should_fail_request_field_not_sent() throws Exception {

        final String idTo = "asdfr124-323-ddf33";

        Map<String, Object> map = new HashMap<>();
        map.put("idTo", idTo);
        map.put("amount", new BigDecimal(120));
        getJsonResponsePut("http://localhost:15000/operation/move", map);
    }

    @Test
    public void should_move_money_between_accounts() throws Exception {

        final String idFrom = "asdfr1uj-456-ggf33";
        final String idTo = "asdfr124-323-ddf33";

        Map<String, Object> map = new HashMap<>();
        map.put("idFrom", idFrom);
        map.put("idTo", idTo);
        map.put("amount", new BigDecimal(120));
        JsonObject response = getJsonResponsePut("http://localhost:15000/operation/move", map);

        JsonElement idFromResponse = response.get("idFrom");
        JsonElement idToResponse = response.get("idTo");
        JsonElement amountRemaining = response.get("amountRemaining");
        JsonElement amountToMove = response.get("amountToMove");

        Assert.assertEquals(idFromResponse.getAsString(), idFrom);
        Assert.assertEquals(idToResponse.getAsString(), idTo);
        Assert.assertEquals(new BigDecimal(amountRemaining.getAsString()), new BigDecimal(10380));
        Assert.assertEquals(new BigDecimal(amountToMove.getAsString()), new BigDecimal(120));
    }

}

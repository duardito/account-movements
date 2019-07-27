package com.revolut.integration;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.revolut.infraestructure.BadRequestException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class BaseTest {

    protected static final String OPERATION_MOVE = "http://localhost:15000/operation/move";

    protected JsonObject getJsonResponsePut(final String url, final Map<String, Object> mapParams) throws Exception {

        try{

            final DefaultHttpClient httpclient = new DefaultHttpClient();

            final HttpPut post = new HttpPut(url);
            final List<NameValuePair> params = getParams(mapParams);
            post.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

            final HttpResponse response = httpclient.execute(post);

            if(response.getStatusLine().getStatusCode() == 400){
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                outputStream.write("Error:".getBytes(Charset.forName("UTF-8")));
                throw new BadRequestException(outputStream);
            }
            final HttpEntity entity = response.getEntity();
            return getResponse(entity.getContent());

        }catch ( Exception e){
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            outputStream.write("Error:".getBytes(Charset.forName("UTF-8")));
            throw new BadRequestException(outputStream);

        }

    }


    private List<NameValuePair> getParams(final Map<String, Object> map) {
        final List<NameValuePair> params = new ArrayList<NameValuePair>(0);
        if(map != null){
            final Iterator entries = map.entrySet().iterator();
            while (entries.hasNext()) {
                final Map.Entry thisEntry = (Map.Entry) entries.next();
                final String key = thisEntry.getKey().toString();
                final String value = thisEntry.getValue().toString();
                params.add(new BasicNameValuePair(key, value));
            }
        }
        return params;
    }

    private JsonObject getResponse(final InputStream is) throws IOException {
        final InputStreamReader isr = new InputStreamReader(is, "utf-8");
        final BufferedReader br = new BufferedReader(isr);
        final String query = br.readLine();
        final JsonParser jsonParser = new JsonParser();
        return (JsonObject) jsonParser.parse(query);
    }
}

/*
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright @2011 the original author or authors.
 */
package com.agile_coder.poker.server.stories.steps;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PutMethod;
import org.jbehave.core.annotations.BeforeScenario;

import com.agile_coder.poker.server.Main;
import com.agile_coder.poker.server.model.Session;

public class BaseSteps {

    private Thread server;
    protected static final String BASE_URL = "http://localhost:8080/poker";

    @BeforeScenario
    public void setup() {
        Session.getInstance().reset();
    }

    protected void startServer() {
        Runnable runner = new Runnable() {

            @Override
            public void run() {
                try {
                    Main.start(8080);
                } catch (InterruptedException e) {
                    try {
                        Main.stop();
                    } catch (Exception ex) {

                    }
                } catch (Exception e) {

                }

            }

        };
        server = new Thread(runner);
        server.run();
    }

    protected void stopServer() {
        if (!server.isInterrupted())
            server.interrupt();
        while (server.isAlive()) {}
    }

    protected int submitEstimate(String name, String vote) throws IOException {
        String VOTE_URL = BASE_URL + "/" + name + "/" + vote;
        HttpClient client = new HttpClient();
        PutMethod put = new PutMethod(VOTE_URL);
        return executeMethod(client, put);
    }

    protected String getStatus() throws IOException {
        HttpClient client = new HttpClient();
        GetMethod get = new GetMethod(BASE_URL);
        int result;
        String response;
        try {
            client.executeMethod(get);
            result = get.getStatusCode();
            response = get.getResponseBodyAsString();
        } finally {
            get.releaseConnection();
        }
        assertEquals(HttpStatus.SC_OK, result);
        return response;
    }

    protected void reveal() throws IOException {
        HttpClient client = new HttpClient();
        String revealUrl = BASE_URL + "/reveal";
        PutMethod put = new PutMethod(revealUrl);
        int result = executeMethod(client, put);
        assertEquals(HttpStatus.SC_NO_CONTENT, result);

    }

    private int executeMethod(HttpClient client, HttpMethod method) throws IOException {
        int result;
        try {
            result = client.executeMethod(method);
        } finally {
            method.releaseConnection();
        }
        return result;
    }
}

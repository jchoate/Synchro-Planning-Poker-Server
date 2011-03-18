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
package com.agile_coder.poker.server.rest;

import static org.junit.Assert.assertEquals;
import net.sf.json.JSONObject;

import org.junit.Before;
import org.junit.Test;

import com.agile_coder.poker.server.SessionManager;
import com.agile_coder.poker.server.model.Session;

public class ViewRequestHandlerTest {

    private static final String NAME = "Test";
    private static final String ESTIMATE = "3";

    @Before
    public void resetSession() {
        Session.getInstance().reset();
    }

    @Test
    public void emptyList() {
        int sess = SessionManager.createSession();
        JSONObject expected = JSONObject.fromObject("{}");
        ViewRequestHandler handler = new ViewRequestHandler();
        String result = handler.viewCurrentVoting(sess);
        assertEquals(expected, JSONObject.fromObject(result));
    }

    @Test
    public void beforeReveal() {
        int sess = SessionManager.createSession();
        final JSONObject expected = JSONObject.fromObject("{Test: null}");
        EstimateRequestHandler estimate = new EstimateRequestHandler();
        estimate.addEstimate(sess, NAME, ESTIMATE);
        ViewRequestHandler handler = new ViewRequestHandler();
        String result = handler.viewCurrentVoting(sess);
        assertEquals(expected, JSONObject.fromObject(result));
    }

    @Test
    public void afterReveal() {
        int sess = SessionManager.createSession();
        final JSONObject expected = JSONObject.fromObject("{Test: 'THREE'}");
        EstimateRequestHandler estimate = new EstimateRequestHandler();
        estimate.addEstimate(sess, NAME, ESTIMATE);
        RevealRequestHandler reveal = new RevealRequestHandler();
        reveal.reveal(sess);
        ViewRequestHandler handler = new ViewRequestHandler();
        String result = handler.viewCurrentVoting(sess);
        assertEquals(expected, JSONObject.fromObject(result));
    }

    @Test
    public void reset() {
        int sess = SessionManager.createSession();
        JSONObject expected = JSONObject.fromObject("{}");
        EstimateRequestHandler estimate = new EstimateRequestHandler();
        estimate.addEstimate(sess, NAME, ESTIMATE);
        ViewRequestHandler handler = new ViewRequestHandler();
        handler.resetSession(sess);
        String result = handler.viewCurrentVoting(sess);
        assertEquals(expected, JSONObject.fromObject(result));
    }

}

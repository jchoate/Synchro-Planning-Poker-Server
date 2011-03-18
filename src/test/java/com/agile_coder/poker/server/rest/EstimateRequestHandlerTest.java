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

import java.util.Map;

import javax.ws.rs.core.Response;

import org.apache.commons.httpclient.HttpStatus;
import org.junit.Before;
import org.junit.Test;

import com.agile_coder.poker.server.SessionManager;
import com.agile_coder.poker.server.model.Estimate;
import com.agile_coder.poker.server.model.Session;

public class EstimateRequestHandlerTest {

    private static final String NAME = "Test";
    private static final String ESTIMATE = "three";

    @Before
    public void resetSession() {
        Session.getInstance().reset();
    }

    @Test
    public void addEstimate() {
        int sess = SessionManager.createSession();
        EstimateRequestHandler handler = new EstimateRequestHandler();
        handler.addEstimate(sess, NAME, ESTIMATE);
        SessionManager.getSession(sess).reveal();
        Map<String, Estimate> estimates = SessionManager.getSession(sess).getEstimates();
        Estimate expected = Estimate.valueOf(ESTIMATE.toUpperCase());
        assertEquals(expected, estimates.get(NAME));
    }

    @Test
    public void duplicateEstimate() {
        int sess = SessionManager.createSession();
        final String estimate2 = "5";
        EstimateRequestHandler handler = new EstimateRequestHandler();
        handler.addEstimate(sess, NAME, ESTIMATE);
        Response resp = handler.addEstimate(sess, NAME, estimate2);
        assertEquals(HttpStatus.SC_CONFLICT, resp.getStatus());
    }

    @Test
    public void afterReveal() {
        int sess = SessionManager.createSession();
        Session session = SessionManager.getSession(sess);
        session.reveal();
        EstimateRequestHandler handler = new EstimateRequestHandler();
        Response resp = handler.addEstimate(sess, NAME, ESTIMATE);
        assertEquals(HttpStatus.SC_CONFLICT, resp.getStatus());
    }
}

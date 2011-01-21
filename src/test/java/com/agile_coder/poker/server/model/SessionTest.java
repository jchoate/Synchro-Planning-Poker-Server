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
package com.agile_coder.poker.server.model;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class SessionTest {

    private static final String NAME = "Test";
    private static final Estimate ESTIMATE = Estimate.EIGHT;

    @Before
    public void reset() {
        Session.getInstance().reset();
    }

    @Test
    public void addVote() {
        Session session = Session.getInstance();
        session.addEstimate(NAME, ESTIMATE);
        assertEquals(1, session.getEstimates().size());
    }

    @Test
    public void resetSession() {
        Session session = Session.getInstance();
        session.addEstimate(NAME, ESTIMATE);
        session.reset();
        assertEquals(0, session.getEstimates().size());
    }

    @Test(expected = com.agile_coder.poker.server.SubmitException.class)
    public void doubleAdd() {
        Session session = Session.getInstance();
        session.addEstimate(NAME, ESTIMATE);
        session.addEstimate(NAME, Estimate.FIVE);
    }

    @Test(expected = com.agile_coder.poker.server.SubmitException.class)
    public void afterReveal() {
        Session session = Session.getInstance();
        session.reveal();
        session.addEstimate(NAME, ESTIMATE);
    }

    @Test
    public void getEstimatesBeforeReveal() {
        Session session = Session.getInstance();
        session.addEstimate(NAME, ESTIMATE);
        Map<String, Estimate> estimates = session.getEstimates();
        assertEquals(null, estimates.get(NAME));
    }

    @Test
    public void getEstimatesAfterReveal() {
        Session session = Session.getInstance();
        session.addEstimate(NAME, ESTIMATE);
        session.reveal();
        Map<String, Estimate> estimates = session.getEstimates();
        assertEquals(ESTIMATE, estimates.get(NAME));
    }

}

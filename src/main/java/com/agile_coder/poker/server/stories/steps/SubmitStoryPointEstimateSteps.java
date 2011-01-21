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

import org.apache.commons.httpclient.HttpStatus;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

/**
 * Submitting a story point estimate
 */

public class SubmitStoryPointEstimateSteps extends BaseSteps {

    private static final String USER_NAME = "Test";

    private int result;

    @Given("the system is running")
    public void setupSession() throws IOException {
        startServer();
    }

    @When("I submit my estimate")
    public void sendEstimate() throws IOException {
        try {
            result = submitEstimate(USER_NAME, "13");
        } finally {
            stopServer();
        }
    }

    @Then("the server records my estimate")
    public void validateSubmit() {
        assertEquals(HttpStatus.SC_NO_CONTENT, result);
    }
}

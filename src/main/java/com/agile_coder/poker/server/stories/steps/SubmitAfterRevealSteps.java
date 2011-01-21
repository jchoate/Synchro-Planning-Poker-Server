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

public class SubmitAfterRevealSteps extends BaseSteps {

    private int result;

    @Given("the estimates have been unlocked")
    public void unlock() throws IOException {
        startServer();
        submitEstimate("Test1", "3");
        submitEstimate("Test2", "5");
        submitEstimate("Test3", "3");
        reveal();
    }

    @When("I submit my estimate")
    public void submit() throws IOException {
        try {
            result = submitEstimate("Test4", "5");
        } finally {
            stopServer();
        }
    }

    @Then("it is rejected")
    public void validate() {
        assertEquals(HttpStatus.SC_CONFLICT, result);
    }

}

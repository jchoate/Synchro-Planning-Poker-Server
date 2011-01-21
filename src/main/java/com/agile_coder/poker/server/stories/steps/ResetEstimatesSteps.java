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

import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.DeleteMethod;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

public class ResetEstimatesSteps extends BaseSteps {

    @Given("that estimates have been revealed")
    public void reveal() throws IOException {
        startServer();
        int result = submitEstimate("Jon", "3");
        assertEquals(HttpStatus.SC_NO_CONTENT, result);
    }

    @When("I reset the estimates")
    public void reset() throws IOException {
        HttpClient client = new HttpClient();
        DeleteMethod delete = new DeleteMethod(BASE_URL);
        client.executeMethod(delete);
        assertEquals(HttpStatus.SC_NO_CONTENT, delete.getStatusCode());
    }

    @Then("all estimates are deleted")
    public void ensureEmptyEstimates() throws IOException {
        String status;
        try {
            status = getStatus();
        } finally {
            stopServer();
        }
        JSONObject expected = JSONObject.fromObject("{}");
        JSONObject observed = JSONObject.fromObject(status);
        assertEquals(expected, observed);
    }

}

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

import org.junit.Test;

public class EstimateTest {

    @Test
    public void stringConvert() {
        Estimate myEstimate = Estimate.valueOf("EIGHT");
        assertEquals(Estimate.EIGHT, myEstimate);
    }

    @Test
    public void intConvert() {
        Estimate myEstimate = Estimate.fromInt(0);
        assertEquals(Estimate.ZERO, myEstimate);
        myEstimate = Estimate.fromInt(1);
        assertEquals(Estimate.ONE, myEstimate);
        myEstimate = Estimate.fromInt(2);
        assertEquals(Estimate.TWO, myEstimate);
        myEstimate = Estimate.fromInt(3);
        assertEquals(Estimate.THREE, myEstimate);
        myEstimate = Estimate.fromInt(5);
        assertEquals(Estimate.FIVE, myEstimate);
        myEstimate = Estimate.fromInt(8);
        assertEquals(Estimate.EIGHT, myEstimate);
        myEstimate = Estimate.fromInt(13);
        assertEquals(Estimate.THIRTEEN, myEstimate);
        myEstimate = Estimate.fromInt(20);
        assertEquals(Estimate.TWENTY, myEstimate);
        myEstimate = Estimate.fromInt(40);
        assertEquals(Estimate.FORTY, myEstimate);
        myEstimate = Estimate.fromInt(100);
        assertEquals(Estimate.ONEHUNDRED, myEstimate);
    }

    @Test(expected = RuntimeException.class)
    public void invalidValue() {
        Estimate myEstimate = Estimate.fromInt(35);
    }

}

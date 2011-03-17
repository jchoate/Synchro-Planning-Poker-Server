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

import java.util.HashMap;
import java.util.Map;

import com.agile_coder.poker.server.SubmitException;

public class Session {

    private static volatile Session sessionSingleton;

    private HashMap<String, Estimate> estimates = new HashMap<String, Estimate>();
    private boolean revealed;

    public Session() {}

    public static Session getInstance() {
        Session session = sessionSingleton;
        if (session == null) {
            synchronized (Session.class) {
                session = sessionSingleton;
                if (session == null) {
                    sessionSingleton = session = new Session();
                }
            }
        }
        return sessionSingleton;
    }

    public synchronized void addEstimate(String name, Estimate estimate) {
        checkIfRevealed();
        checkForExistingEstimate(name);
        estimates.put(name, estimate);
    }

    private void checkIfRevealed() {
        if (revealed) {
            throw new SubmitException();
        }
    }

    private void checkForExistingEstimate(String name) {
        if (estimates.containsKey(name)) {
            throw new SubmitException();
        }
    }

    public synchronized void reset() {
        estimates = new HashMap<String, Estimate>();
        revealed = false;
    }

    public synchronized Map<String, Estimate> getEstimates() {
        Map<String, Estimate> map = new HashMap<String, Estimate>();
        if (revealed) {
            for (String key : estimates.keySet()) {
                map.put(key, estimates.get(key));
            }
        } else {
            for (String key : estimates.keySet()) {
                map.put(key, null);
            }
        }
        return map;
    }

    public synchronized void reveal() {
        revealed = true;
    }

}
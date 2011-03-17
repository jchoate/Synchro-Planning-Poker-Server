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
package com.agile_coder.poker.server;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.agile_coder.poker.server.model.Session;

public class SessionManagerTest {

    @Test
    public void getSession() {
        int sessionId = SessionManager.createSession();
        Session sess = SessionManager.getSession(sessionId);
        assertNotNull(sess);
    }

    @Test
    public void GetTwoSessions() {
        int session1 = SessionManager.createSession();
        int session2 = SessionManager.createSession();
        Session s1 = SessionManager.getSession(session1);
        Session s2 = SessionManager.getSession(session2);
        assertFalse(session1 == session2);
        assertFalse(s1.equals(s2));
    }

}

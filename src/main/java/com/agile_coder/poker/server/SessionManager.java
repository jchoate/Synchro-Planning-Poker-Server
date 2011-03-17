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

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

import com.agile_coder.poker.server.model.Session;

public class SessionManager {

    private static final Map<SessionId, Session> sessions = new HashMap<SessionId, Session>();

    public static int createSession() {
        int id = new SecureRandom().nextInt();
        SessionId sId = new SessionId(id);
        sessions.put(sId, new Session());
        return id;
    }

    public static Session getSession(int sessionId) {
        return sessions.get(new SessionId(sessionId));
    }

    private static class SessionId {

        private int id;

        public SessionId(final int id) {
            this.id = id;
        }

        @Override
        public boolean equals(Object other) {
            if (!(other instanceof SessionId))
                return false;
            return id == ((SessionId) other).id;
        }

        @Override
        public int hashCode() {
            return id;
        }
    }

}

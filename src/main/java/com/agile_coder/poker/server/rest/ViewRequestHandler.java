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

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import net.sf.json.JSONObject;

import com.agile_coder.poker.server.SessionManager;
import com.agile_coder.poker.server.model.Session;

@Path("/{session}")
public class ViewRequestHandler {

    @GET
    @Produces("application/json")
    public String viewCurrentVoting(@PathParam("session") final int session) {
        Session s = SessionManager.getSession(session);
        return JSONObject.fromObject(s.getEstimates()).toString();
    }

    @DELETE
    public void resetSession(@PathParam("session") final int session) {
        Session s = SessionManager.getSession(session);
        s.reset();
    }

}

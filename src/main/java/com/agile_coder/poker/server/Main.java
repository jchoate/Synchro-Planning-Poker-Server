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

import org.mortbay.jetty.Server;
import org.mortbay.jetty.handler.ContextHandlerCollection;
import org.mortbay.jetty.servlet.Context;
import org.mortbay.jetty.servlet.ServletHolder;

import com.sun.jersey.spi.container.servlet.ServletContainer;

public class Main {

    private static Server webServer;

    public static void main(String[] args) throws Exception {
        if (args.length == 1 && isInteger(args[0])) {
            start(Integer.parseInt(args[0]));
        } else {
            showUsage();
        }
    }

    public static void start(int port) throws Exception {
        webServer = new Server(port);
        final ContextHandlerCollection collection = new ContextHandlerCollection();
        final Context ctx = new Context(collection, "/poker", Context.NO_SESSIONS
                                                              | Context.NO_SECURITY);
        ctx.setAllowNullPathInfo(true);
        final ServletHolder holder = new ServletHolder(new ServletContainer());
        holder.setInitParameter(
                "com.sun.jersey.config.property.packages",
                "com.agile_coder.poker.server.rest");
        holder.setInitOrder(1);
        ctx.addServlet(holder, "/*");
        webServer.addHandler(collection);
        webServer.start();
    }

    public static void stop() throws Exception {
        webServer.stop();
    }

    private static boolean isInteger(String candidate) {
        try {
            Integer.parseInt(candidate);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    private static void showUsage() {
        System.out
                .println("Usage:  com.agile_coder.poker.server.Main <port>/n/tWhere port is the port on which the server should listen");
    }

}

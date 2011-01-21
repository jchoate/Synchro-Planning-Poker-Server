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

public enum Estimate {
    ZERO, ONE, TWO, THREE, FIVE, EIGHT, THIRTEEN, TWENTY, FORTY, ONEHUNDRED, BIO, INFINITY, UNKNOWN;

    public static Estimate fromInt(int num) {
        switch (num) {
            case 0:
                return ZERO;
            case 1:
                return ONE;
            case 2:
                return TWO;
            case 3:
                return THREE;
            case 5:
                return FIVE;
            case 8:
                return EIGHT;
            case 13:
                return THIRTEEN;
            case 20:
                return TWENTY;
            case 40:
                return FORTY;
            case 100:
                return ONEHUNDRED;
            default:
                throw new IllegalArgumentException();
        }

    }
}

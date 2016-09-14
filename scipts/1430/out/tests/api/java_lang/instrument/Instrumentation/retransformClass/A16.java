/*
 * Copyright (c) 2008, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package javasoft.sqe.tests.api.java.lang.instrument.Instrumentation.retransformClass;

public class A16 {
    public static int staticField = 33;

    static {
        staticField++;
    }

    public int instanceField = 22;

    public static int staticMethod() {
        String s2 = "this is a string in static method";
        return 3;
    }

    public int instanceMethod() {
        return 2;
    }
}

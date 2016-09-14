/*
 * Copyright (c) 2008, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package javasoft.sqe.tests.api.java.lang.instrument.Instrumentation.retransformClass;

public class A17 {
    public static int staticField = 3333;

    static {
        staticField++;
    }

    public int instanceField = 2222;

    public static int staticMethod() {
        String s2 = "this is a string in static method";
        return 333;
    }

    public int instanceMethod() {
        return 222;
    }
}

/*
 * Copyright (c) 2007, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package javasoft.sqe.tests.api.java.lang.instrument.Instrumentation.retransformClass;

public class A1 {
    public static int staticField = 33;

    static {
        staticField++;
    }

    public int instanceField = 22;

    public static int staticMethod() {
        return 3;
    }

    public int instanceMethod() {
        return 2;
    }
}


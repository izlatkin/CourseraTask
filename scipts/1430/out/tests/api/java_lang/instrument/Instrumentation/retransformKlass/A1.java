/*
 * Copyright (c) 2007, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package javasoft.sqe.tests.api.java.lang.instrument.Instrumentation.retransformKlass;

public class A1 {
    public static int staticField = 55;

    static {
        staticField++;
    }

    public int instanceField = 44;

    public static int staticMethod() {
        return 5;
    }

    public int instanceMethod() {
        return 4;
    }
}


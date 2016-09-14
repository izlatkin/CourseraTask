/*
 * Copyright (c) 2007, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package javasoft.sqe.tests.api.java.lang.instrument.Instrumentation.retransformClass;

public class A7 {
    public static int staticMethod() {
        return 3;
    }

    public int instanceMethod() { //removed in retransformed class
        return 2;
    }
}

/*
 * Copyright (c) 2007, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package javasoft.sqe.tests.api.java.lang.instrument.Instrumentation.retransformKlass;

public class A10 {
    public static int staticMethod() { //added in retransformed class
        return 3;
    }

    public int instanceMethod() { 
        return 2;
    }
}

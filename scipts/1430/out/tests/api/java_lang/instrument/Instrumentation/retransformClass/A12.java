/*
 * Copyright (c) 2007, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package javasoft.sqe.tests.api.java.lang.instrument.Instrumentation.retransformClass;

public class A12 {
    public static int staticField = 33;
    transient int instanceField = 22; //will be volatile in retransformed class
}

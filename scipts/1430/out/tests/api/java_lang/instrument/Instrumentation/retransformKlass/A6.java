/*
 * Copyright (c) 2007, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package javasoft.sqe.tests.api.java.lang.instrument.Instrumentation.retransformKlass;

public class A6 {
    public static int staticField = 33; //added in retransformed class
    public int instanceField = 22;
}
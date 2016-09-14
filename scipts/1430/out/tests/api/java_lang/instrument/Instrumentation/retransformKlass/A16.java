/*
 * Copyright (c) 2008, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package javasoft.sqe.tests.api.java.lang.instrument.Instrumentation.retransformKlass;

public class A16 {
    public static int staticField = 55;

    static {
        staticField++;
    }

    public int instanceField = 44;

    public int instanceMethod() {
        int i = 0;
        i++;
        String s1 = "this is a string in instance method";
        return 4;
    }

    public static int staticMethod() {
        int j = 0;
        j--;
        return 5;
    }
}

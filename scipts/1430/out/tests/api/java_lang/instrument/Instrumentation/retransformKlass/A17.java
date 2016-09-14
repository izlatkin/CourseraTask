/*
 * Copyright (c) 2008, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package javasoft.sqe.tests.api.java.lang.instrument.Instrumentation.retransformKlass;

public class A17 {
    public static int staticField = 5555;

    static {
        staticField++;
    }

    public int instanceField = 4444;

    public int instanceMethod() {
        int i = 0;
        i++;
        String s1 = "this is a string in instance method";
        return 444;
    }

    public static int staticMethod() {
        int j = 0;
        j--;
        return 555;
    }
}

/*
 * @(#)NMPA4.java 08/01/29
 *
 * Copyright (c) 2008, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package javasoft.sqe.tests.api.java.lang.instrument.Instrumentation.snmp;


public class NMPA4 {
    native public static boolean regMeth();

    public int nmpa4() {
        return wrappednmpa4();
    }

    native public int wrappednmpa4();
}

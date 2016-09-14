/*
 * @(#)NMPA1.java 08/01/29
 *
 * Copyright (c) 2008, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package javasoft.sqe.tests.api.java.lang.instrument.Instrumentation.snmp;


public class NMPA1 {
    public int nmpa1() {
        return wrappednmpa1();
    }

    native public int wrappednmpa1();
}

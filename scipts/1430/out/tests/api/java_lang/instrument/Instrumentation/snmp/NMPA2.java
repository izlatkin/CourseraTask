/*
 * @(#)NMPA2.java 08/01/29
 *
 * Copyright (c) 2008, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package javasoft.sqe.tests.api.java.lang.instrument.Instrumentation.snmp;


public class NMPA2 {
    public int nmpa2() {
        return wrappednmpa2();
    }

    native public int wrappednmpa2();
}

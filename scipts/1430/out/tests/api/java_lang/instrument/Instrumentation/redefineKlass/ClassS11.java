/*
 * @(#)ClassS11.java 06/10/03Cheryl Stocks, Oleg Oleinik
 *
 * Copyright (c) 2003, 2006, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

/*
 * Copyright (c) 2003 Altaworks Corporation, Nashua, NH  U.S.A.
 */

package javasoft.sqe.tests.api.java.lang.instrument.Instrumentation.redefineKlass;

import javasoft.sqe.tests.api.java.lang.instrument.Instrumentation.ClassS;
import javasoft.sqe.tests.api.java.lang.instrument.Instrumentation.InterfaceS11;

public class ClassS11 extends ClassS implements InterfaceS11 {

    public String getStringValue(){
        return "redefined";
    }

    public int getIntValue(){
        return 100;
    }
}


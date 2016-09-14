/*
 * @(#)ClassS10.java 06/10/03Cheryl Stocks, Oleg Oleinik
 *
 * Copyright (c) 2003, 2006, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

/*
 * Copyright (c) 2003 Altaworks Corporation, Nashua, NH  U.S.A.
 */

package javasoft.sqe.tests.api.java.lang.instrument.Instrumentation.redefineKlass;

import javasoft.sqe.tests.api.java.lang.instrument.Instrumentation.ClassS0;

public class ClassS10 extends ClassS0 {

    public String getStringValue(){
        return "redefined";
    }

    public int getIntValue(){
        return 100;
    }
}


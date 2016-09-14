/*
 * @(#)ClassS3.java 06/10/03Cheryl Stocks, Oleg Oleinik
 *
 * Copyright (c) 2003, 2006, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

/*
 * Copyright (c) 2003 Altaworks Corporation, Nashua, NH  U.S.A.
 */

package javasoft.sqe.tests.api.java.lang.instrument.Instrumentation.redefineClass;

import javasoft.sqe.tests.api.java.lang.instrument.Instrumentation.ClassS;

public class ClassS3 extends ClassS {

    public String str = "not redefined";
    
    public String getStringValue(){
        return str;
    }

    public int getIntValue(){
        return -100;
    }
}

/*
 * @(#)ClassG1.java 06/10/03Cheryl Stocks, Oleg Oleinik
 *
 * Copyright (c) 2003, 2006, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

/*
 * Copyright (c) 2003 Altaworks Corporation, Nashua, NH  U.S.A.
 */

package javasoft.sqe.tests.api.java.lang.instrument.Instrumentation.redefineClass;

import javasoft.sqe.tests.api.java.lang.instrument.Instrumentation.ClassG;

public class ClassG1 extends ClassG {
    
    public static int x = -1000;

    public int getInt(){
        return x;
    }
}

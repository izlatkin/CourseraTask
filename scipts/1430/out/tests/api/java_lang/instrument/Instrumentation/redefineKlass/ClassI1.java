/*
 * @(#)ClassI1.java 06/10/03Cheryl Stocks, Oleg Oleinik
 *
 * Copyright (c) 2003, 2006, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

/*
 * Copyright (c) 2003 Altaworks Corporation, Nashua, NH  U.S.A.
 */

package javasoft.sqe.tests.api.java.lang.instrument.Instrumentation.redefineKlass;

public class ClassI1 {
    
    public String s = "Redefined string for transformation, ClassI1"; // 44 ASCI chars

    public String getString(){
        return s;
    }
}

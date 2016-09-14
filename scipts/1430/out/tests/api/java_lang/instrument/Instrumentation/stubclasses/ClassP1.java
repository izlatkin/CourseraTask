/*
 * @(#)ClassP1.java 06/10/03Cheryl Stocks, Oleg Oleinik
 *
 * Copyright (c) 2003, 2006, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

/*
 * Copyright (c) 2003 Altaworks Corporation, Nashua, NH  U.S.A.
 */

import javasoft.sqe.tests.api.java.lang.instrument.Instrumentation.BaseClass;

public class ClassP1 extends BaseClass {
    String s = " T r a n s f o r m e d    string   , ClassP1";

    public String getString(){
        return s;
    }
}


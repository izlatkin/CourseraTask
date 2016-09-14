/*
 * @(#)ClassD1.java 06/10/03Cheryl Stocks, Oleg Oleinik
 *
 * Copyright (c) 2003, 2006, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

/*
 * Copyright (c) 2003 Altaworks Corporation, Nashua, NH  U.S.A.
 */

package javasoft.sqe.tests.api.java.lang.instrument.Instrumentation.redefineKlass;

import javasoft.sqe.tests.api.java.lang.instrument.Instrumentation.ClassD;

public class ClassD1 extends ClassD {

    {
        classID = "ClassD1";

        s = "Redefined class " + classID;
   
        x = 100;
    }
    
    int y = -10;
    String s2 = "Redefined";
        
    public String getStr1(){
        return s;
    }

    public String getStr2(){
        return s2;
    }
    
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}


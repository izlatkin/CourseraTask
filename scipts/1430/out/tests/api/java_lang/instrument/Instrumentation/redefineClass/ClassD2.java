/*
 * @(#)ClassD2.java 06/10/03Cheryl Stocks, Oleg Oleinik
 *
 * Copyright (c) 2003, 2006, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

/*
 * Copyright (c) 2003 Altaworks Corporation, Nashua, NH  U.S.A.
 */

package javasoft.sqe.tests.api.java.lang.instrument.Instrumentation.redefineClass;

import javasoft.sqe.tests.api.java.lang.instrument.Instrumentation.ClassD;

public class ClassD2 extends ClassD {

    {
        classID = "ClassD2";

        s = "Not redefined class " + classID;
   
        x = -10;
    }
    
    int y = 100;
    String s2 = "Not redefined";
        
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

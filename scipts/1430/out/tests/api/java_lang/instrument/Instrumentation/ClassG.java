/*
 * Copyright (c) 2003, 2006, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
 
 /*
 * @(#)ClassG.java 06/06/08Cheryl Stocks, Oleg Oleinik
 */

/*
 * Copyright (c) 2003 Altaworks Corporation, Nashua, NH  U.S.A.
 */

package javasoft.sqe.tests.api.java.lang.instrument.Instrumentation;

public class ClassG {
    
    public static boolean checkOldValues(ClassG obj){
        if (-1000 != obj.getInt()){
            return false;
        }
        return true;
    }
    
    public static boolean checkNewValues(ClassG obj){
        if (-1000 != obj.getInt()){
            return false;
        }
        return true;
    }

    public int getInt(){
        return 0;
    }
}

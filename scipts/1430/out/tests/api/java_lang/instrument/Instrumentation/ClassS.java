/*
 * Copyright (c) 2003, 2006, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

/*
 * @(#)ClassS.java 06/06/08Cheryl Stocks, Oleg Oleinik
 */
 
/*
 * Copyright (c) 2003 Altaworks Corporation, Nashua, NH  U.S.A.
 */

package javasoft.sqe.tests.api.java.lang.instrument.Instrumentation;

public class ClassS {
    
    public String getStringValue(){
        return "";
    }

    public int getIntValue(){
        return 0;
    }
    
    public static boolean checkOldValues(ClassS obj){
        if (!"not redefined".equals(obj.getStringValue())){
            return false;
        }
        if (-100 != obj.getIntValue()){
            return false;
        }
        return true;
    }

}

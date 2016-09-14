/*
 * Copyright (c) 2003, 2006, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
 
/*
 * @(#)ClassD.java 06/06/08Cheryl Stocks, Oleg Oleinik
 */
 
/*
 * Copyright (c) 2003 Altaworks Corporation, Nashua, NH  U.S.A.
 */

package javasoft.sqe.tests.api.java.lang.instrument.Instrumentation;

public class ClassD {

    public String s = "xxx";
    public int x = -1;
    public String classID = "";

    public static boolean checkNewValues(ClassD obj){

        String expectedValue = "Redefined class " + obj.classID;

        if (!expectedValue.equals(obj.getStr1())){
            return false;
        }
        
        if (!"Redefined".equals(obj.getStr2())){
            return false;
        }

        if (100 != obj.getX()){
            return false;
        }

        if (-10 != obj.getY()){
            return false;
        }

        return true;
    }

    public static boolean checkOldValues(ClassD obj){

        String expectedValue = "Not redefined class " + obj.classID;
        if (!expectedValue.equals(obj.getStr1())){
            return false;
        }
        
        if (!"Not redefined".equals(obj.getStr2())){
            return false;
        }

        if (-10 != obj.getX()){
            return false;
        }

        if (100 != obj.getY()){
            return false;
        }
        return true;
    }

    public String getStr1(){
        return "Original class";
    }
    
    public String getStr2(){
        return "";
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return 0;
    }

}

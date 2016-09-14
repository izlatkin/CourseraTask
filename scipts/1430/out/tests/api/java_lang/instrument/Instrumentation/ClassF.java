/*
 * Copyright (c) 2003, 2006, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

/*
 * @(#)ClassF.java 06/06/08Cheryl Stocks, Oleg Oleinik
 */
 
/*
 * Copyright (c) 2003 Altaworks Corporation, Nashua, NH  U.S.A.
 */

package javasoft.sqe.tests.api.java.lang.instrument.Instrumentation;

public class ClassF extends Thread {

    public volatile int x = 0;
       
    public Object synchThreadReady = new Object();
    
    public static volatile int flag = -1;

    public static boolean checkNewValues(int i){
        if (i != -10000){
            return false;
        }
        return true;
    }

    public static boolean checkOldValues(int i){
        if (i != 10000){
            return false;
        }
        return true;
    }

    public int getInt(){
        return x;
    }
    
    public void run(){
    }
}

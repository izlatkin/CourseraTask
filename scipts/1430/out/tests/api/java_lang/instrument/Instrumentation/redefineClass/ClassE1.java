/*
 * @(#)ClassE1.java 06/10/03Cheryl Stocks, Oleg Oleinik
 *
 * Copyright (c) 2003, 2006, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

/*
 * Copyright (c) 2003 Altaworks Corporation, Nashua, NH  U.S.A.
 */

package javasoft.sqe.tests.api.java.lang.instrument.Instrumentation.redefineClass;

import javasoft.sqe.tests.api.java.lang.instrument.Instrumentation.ClassE;

public class ClassE1 extends ClassE {

    public void run(){
        synchronized (synchThreadReady){
            runIt();
            try {
                while (ClassE.flag != 0){
                    synchThreadReady.wait();
                }
            } catch (InterruptedException ie){
                return;
            }
            runIt();
        }
    }

    public void runIt(){
        ClassE.flag = 1;
        synchThreadReady.notifyAll();
        try {
            while (ClassE.flag != 2){
                synchThreadReady.wait();
            }
        } catch (InterruptedException ie){
            return;
        }
        x = 10000;
        ClassE.flag = 3;
        synchThreadReady.notifyAll();
    }
    
}

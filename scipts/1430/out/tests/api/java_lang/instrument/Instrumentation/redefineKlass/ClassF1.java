/*
 * @(#)ClassF1.java 06/10/03Cheryl Stocks, Oleg Oleinik
 *
 * Copyright (c) 2003, 2006, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

/*
 * Copyright (c) 2003 Altaworks Corporation, Nashua, NH  U.S.A.
 */

package javasoft.sqe.tests.api.java.lang.instrument.Instrumentation.redefineKlass;

import javasoft.sqe.tests.api.java.lang.instrument.Instrumentation.ClassF;

public class ClassF1 extends ClassF {

    public void run(){
        synchronized (synchThreadReady){
            runIt(this);
            try {
                while (ClassF.flag != 0){
                    synchThreadReady.wait();
                }
            } catch (InterruptedException ie){
                return;
            }
            runIt(this);
        }
    }

    public static void runIt(ClassF obj){
        ClassF.flag = 1;
        obj.synchThreadReady.notifyAll();
        try {
            while (ClassF.flag != 2){
                obj.synchThreadReady.wait();
            }
        } catch (InterruptedException ie){
            return;
        }
        obj.x = -10000;
        ClassF.flag = 3;
        obj.synchThreadReady.notifyAll();
    }
    
}

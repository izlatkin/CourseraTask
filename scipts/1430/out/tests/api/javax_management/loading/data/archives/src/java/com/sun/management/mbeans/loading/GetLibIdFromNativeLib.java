/*
 * Copyright (c) 2003, Oracle and/or its affiliates. All rights reserved.
 */

package com.sun.management.mbeans.loading;

/**
 * This  class is used to test java native library loader supported by MLet.
 **/
public class GetLibIdFromNativeLib implements java.io.Serializable, GetLibIdFromNativeLibMBean {
	
    /*public native int getRandom(); */
    public native int getLibId() ;
 
    static {
	try {
	    System.loadLibrary("jmxlibid");
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    
}
    

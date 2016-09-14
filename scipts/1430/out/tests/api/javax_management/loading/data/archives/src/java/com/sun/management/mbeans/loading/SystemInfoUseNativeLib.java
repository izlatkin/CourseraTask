/*
 * Copyright (c) 2003, Oracle and/or its affiliates. All rights reserved.
 */

package com.sun.management.mbeans.loading ;

/**
 * This  class is used to test java native library loader supported by MLet.
**/


public class SystemInfoUseNativeLib implements java.io.Serializable, SystemInfoUseNativeLibMBean {
	
    /*public native int getRandom(); */
    public native int getRandom() ;
 
    static {
	try {
	    System.loadLibrary("systemInfo");
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    
}
    

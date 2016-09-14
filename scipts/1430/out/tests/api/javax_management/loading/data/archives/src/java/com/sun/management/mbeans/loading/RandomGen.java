/*
 * Copyright (c) 2003, Oracle and/or its affiliates. All rights reserved.
 */

package com.sun.management.mbeans.loading ;

/**
 * This  class is used to test java native library loader supported by MLet.
**/


public class RandomGen implements java.io.Serializable, RandomGenMBean {
	
    /*public native int getRandom(); */
    public native int getRandom() ;
 
    static {
	try {
	    System.loadLibrary("genrandom");
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    
}
    

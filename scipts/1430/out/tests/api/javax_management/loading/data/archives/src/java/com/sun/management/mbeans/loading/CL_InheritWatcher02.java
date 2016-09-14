/*
 * Copyright (c) 2003, Oracle and/or its affiliates. All rights reserved.
 */

package com.sun.management.mbeans.loading ;


/**
 * This class extends WatchClassLoader. Its management interface extends the
 * base class management interface. It also provides a getter getState().
 * <br> This mBean is used for tests related toJMX tests related to class
 * loading.
 */
public class  CL_InheritWatcher02 extends WatchClassLoader {
    
    /*
     * Default constructor.
     */
    public CL_InheritWatcher02() {
	count = 02 ;
    }

    /**
     * Public getter getCount(). Not exposed for management.
     */
    public int getCount() {
	return count ;
    } 

    private int count = 0 ;

}
		
		
	    

   


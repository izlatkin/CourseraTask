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
public class  CL_InheritWatcher01 extends WatchClassLoader implements CL_InheritWatcher01MBean {
    
    /*
     * Default constructor.
     */
    public CL_InheritWatcher01() {
	state = "Normal" ;
    }

    /**
     * Public getter getState().
     */
    public String getState() {
	return state ;
    } 

    private String state = "" ;

}
		
		
	    

   

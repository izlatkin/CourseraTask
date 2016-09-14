/*
 * Copyright (c) 2003, Oracle and/or its affiliates. All rights reserved.
 */

package com.sun.management.mbeans.loading ;


/**
 * This mBean is used for tests related to JMX tests related to class
 * loading.
 * <br> It has the particularity of providing an operation returning the
 * non-jdk type class CL_InheritWatcher01. Usually both classes are not 
 * packaged into the same jar file.
 */
public class  CL_UseOtherClass implements CL_UseOtherClassMBean {
    
    /**
     * Default Constructor.
     */
    public CL_UseOtherClass() {
	State = "Normal" ;
	Description = "Standard MBean" ;
    }

    /**
     * Factory for CL_InheritWatcher01 class.
     */
    public CL_InheritWatcher01  newCL_InheritWatcher01() {
	return new CL_InheritWatcher01() ;
    } 

    private String State ;
    private String Description ;

}
		
		
	    

   


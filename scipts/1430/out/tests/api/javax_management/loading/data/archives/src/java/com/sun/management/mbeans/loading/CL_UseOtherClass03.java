/*
 * Copyright (c) 2003, Oracle and/or its affiliates. All rights reserved.
 */

package com.sun.management.mbeans.loading ;


/**
 * This mBean is used for tests related to JMX tests related to class
 * loading.
 * <br> This MBean offers for management an operation taking in its a non
 * non-jdk class.
 */
public class  CL_UseOtherClass03 implements CL_UseOtherClass03MBean {
    
    /**
     * Default Constructor.
     */
    public CL_UseOtherClass03() {
	State = "Normal" ;
	Description = "Standard MBean" ;
    }

    /** Operation exposed for management */
    public void copyItsState(MLetBean ref, boolean valid) {
	if ( valid)
	    State = ref.getState () ;
    }

    private String State ;
    private String Description ;

}
		
		
	    

   


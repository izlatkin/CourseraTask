/*
 * Copyright (c) 2003, Oracle and/or its affiliates. All rights reserved.
 */

package com.sun.management.mbeans.loading ;


/**
 * This mBean is used for tests related to JMX tests related to class
 * loading.
 * <br> This MBean has also the default constructor and a constructor taking
 * as argument an other non-jdk class and a String.
 */
public class  CL_UseOtherClass02 implements CL_UseOtherClass02MBean {
    
    /**
     * Default Constructor.
     */
    public CL_UseOtherClass02() {
	State = "Normal" ;
	Description = "Standard MBean" ;
    }

    /**
     * Non default constructor.
     * @param reference an instance of MLetBeanSimple.
     * @param description Description of this MBean.
     */
    public CL_UseOtherClass02(MLetBeanSimple reference, String description ) {
	State = reference.getState() ;
	Description = description ;
    }

    private String State ;
    private String Description ;

}
		
		
	    

   


/*
 * Copyright (c) 2003, Oracle and/or its affiliates. All rights reserved.
 */

package com.sun.management.mbeans.loading ;

import javax.management.ObjectName ;

/**
 * This is the management interface of WatchClassLoader.
 */
public interface  WatchClassLoaderMBean {
    
    /**
     * Getter of the MBean class loader. 
     */
    public ClassLoader getMBeanClassLoader() ; 

    /**
     * Boolean getter.
     */
    public boolean isLoadedWithThisLoader( ObjectName compared) ;

}
		
		
	    

   


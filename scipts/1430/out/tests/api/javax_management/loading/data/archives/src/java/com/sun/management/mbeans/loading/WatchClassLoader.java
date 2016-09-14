/*
 * Copyright (c) 2003, Oracle and/or its affiliates. All rights reserved.
 */

package com.sun.management.mbeans.loading ;

import javax.management.ObjectName ;
import javasoft.sqe.tests.api.javax.management.shared.utils.misc.MyMLet ;

/**
 * WatchClassLoader is the base class of all the MBeans used for classloading
 * tests. Allow the MBeans extending it will have methods allowing to retrieve
 * the reference to the object name of the loader of this class. This loader
 * may either be the system class loader or an instance of MyMLet.
 */
public class  WatchClassLoader implements WatchClassLoaderMBean {
    
    /**
     * Tells the class loader used to instanciate this MBean class.
     * @return The MyMLet used to instanciate the class. Usually, when MyMLet
     * used instead of the JMX MLet class, the returned ClassLoader is an 
     * instance of MymLet unless the MBean class is loaded by the system class
     * loader.
     * @see javasoft.sqe.tests.api.javax.management.loading.MyMLet
     */
    public ClassLoader getMBeanClassLoader() {
	return this.getClass().getClassLoader() ;
    } 


    /**
     * Tells of this MBean has been loaded with the provided class loader.
     * @param compared the object name of loader.
     */
    public boolean isLoadedWithThisLoader(ObjectName compared) {
	ClassLoader loader = getMBeanClassLoader() ;
	if ( ( compared == null ) || !(loader instanceof MyMLet) )
	    return false ;

	return compared.equals(((MyMLet)this.getMBeanClassLoader()).getRegistrationName()) ;
    }

}
		
		
	    

   


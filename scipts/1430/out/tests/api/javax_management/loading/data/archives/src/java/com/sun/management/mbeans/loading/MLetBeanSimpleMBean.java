/*
 * Copyright (c) 2003, Oracle and/or its affiliates. All rights reserved.
 */

package com.sun.management.mbeans.loading ;

/**
 * This is the management interface of a simple standard mbean used for tests
 * related to JMX MLet service.
 **/

public interface MLetBeanSimpleMBean {
    
    public String getInstanceName() ;
    
    public void setInstanceName(String s) ;
    
    public String getState() ;
    
    public void setState(String s) ;
    
    public Integer getNbChanges() ;
    
    public String writeInstanceName() ;

}

 




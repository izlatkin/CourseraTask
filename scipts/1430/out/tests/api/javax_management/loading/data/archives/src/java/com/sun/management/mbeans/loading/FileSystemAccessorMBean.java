/*
 * Copyright (c) 2003, Oracle and/or its affiliates. All rights reserved.
 */

package com.sun.management.mbeans.loading ;

import java.io.IOException ;
/**
 * This is the management interface of FileSystemAccessor.
 * <br> It is used for tests related to JMX MLet service.
**/

public interface FileSystemAccessorMBean {

    public boolean isFSAccessed() ;

    public void createFile(String fullFileName) throws IOException ;

    public String getState() ;
    
    public void setState(String val) ;

}

 




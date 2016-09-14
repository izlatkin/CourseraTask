/*
 * Copyright (c) 2003, Oracle and/or its affiliates. All rights reserved.
 */

package com.sun.management.mbeans.loading ;

import java.io.IOException ;
import java.io.File ;
import java.io.FileOutputStream ;
import java.util.PropertyPermission;

/**
 * This is a simple mbean whose operation createFile() creates a file. This
 * MBeans is used within tests related to loading of (non)signed archives MLet
 * loading. This is because accessing file systems (as in createFile) is 
 * granted to only trusted MLet mbeans.
 **/

public class FileSystemAccessor implements java.io.Serializable, FileSystemAccessorMBean {
    private boolean FSaccessed ;
    private String state ;
    
    public FileSystemAccessor() {
	FSaccessed = false ;
	state = "construction" ;
    }
    
    /** 
     * This getter tells if the File System has already been accessed.
     */
    public boolean isFSAccessed() {
	return FSaccessed ;
    }
    
    /** This method simply access the file system and create a file in the
     * specified location.
     * @param fullFileName the full name of the file to create.
     */
    public void createFile(String fullFileName) throws IOException {
    
    File file = null;

    try {
	    file = File.createTempFile(fullFileName, "tmp" ) ;
    } catch(SecurityException e) {
        SecurityManager sm = System.getSecurityManager();
            if (sm != null) {
                try {
                    // get value of java.io.tmpdir property
                    String tmpDir = null;
                    try {
                        tmpDir = System.getProperty("java.io.tmpdir");
                    } catch (SecurityException se) {
                        // possible exception
                        try {
                            sm.checkPermission(new PropertyPermission("java.io.tmpdir", "read"));
                            throw se;
                        } catch (SecurityException se1) {
                            FSaccessed = true; 
                            return;
                        }
                    }
                    sm.checkWrite(tmpDir);

                } catch (SecurityException se) {
                   FSaccessed = true; 
                   return;
                }
            }    
            throw e;
    }
    
    try {
        if (file != null)
            file.deleteOnExit();
    } catch(SecurityException e) {
    }
    
	// System.out.println("FileSystemAccessor:createFile() called."+ file ) ;
	FSaccessed = true ;
    }

    /** Setter for State attribute */
    public void setState(String value ) {
	state = value ;
    }

    /** Getter for State attribute */
    public String getState() {
	return state ;
    }
}

 




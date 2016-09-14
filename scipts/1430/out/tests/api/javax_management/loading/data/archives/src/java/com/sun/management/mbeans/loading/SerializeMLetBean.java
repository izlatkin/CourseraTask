/*
 * Copyright (c) 2003, Oracle and/or its affiliates. All rights reserved.
 */

/**
 * This class allowing the serialization of the class MLetMBean.
 * <br> Serialization is performed calling the method main.
 */
package com.sun.management.mbeans.loading ;

import java.io.FileOutputStream ;
import java.io.File ;
import java.io.ObjectOutputStream ;
import java.io.IOException ;

import java.util.StringTokenizer ;


public class SerializeMLetBean {
    
    /**
     * This method is called to serialize an instance of MLetBean created on
     * fly. The argument passed to this method is a String telling the JDK 
     * variant (12 or 11). Default is 12!
     */
    public static void main(String args[]) {
	try {
	
	    String jdkvariant = "12" ;
	    if ( ( args.length > 0 ) &&  args[0].equals("11") )
		// update jdk variant accordingly
		jdkvariant = "11" ;
	    // Create MLetBean object
	    MLetBean bean = new MLetBean();
	    
	    // Set properties
	    //
	    
	    bean.setState("ToBeSerialized");
	    bean.setInstanceName("MyMLetBean");
	    
	    // Serialize MLetBean object to a file
	    String rootWanted = "original" + System.getProperty("file.separator") + "classes" ;
	    String topDir = resolveName( rootWanted, System.getProperty("java.class.path") ) ;
	    String fileToCreate = topDir + System.getProperty("file.separator") + "com" + System.getProperty("file.separator") + "sun" + System.getProperty("file.separator") + "management" + System.getProperty("file.separator") + "mbeans"+ System.getProperty("file.separator") + "loading"+  System.getProperty("file.separator") + "MLetBean.ser" ;
	    // write into that file
	    FileOutputStream ostream = new FileOutputStream( fileToCreate );
	    ObjectOutputStream p = new ObjectOutputStream(ostream);
	    p.writeObject(bean);
	    p.flush();
	    ostream.close();
	} catch (IOException ioe) {
	    System.err.println("IOException : " + ioe.getMessage());
	}
    }
    
    
    /** 
     * This method looks for the classpath used to start the main of this 
     * class and looks for the path containing the fileName string.
     * @fileName The file name used to select out the path of classes.
     * @path file name in which the search is made. It is made of paths
     * separated with directory separators (like the java class path).
     * @return The (1st) part of the class path containing fielName.
     */
    private static String resolveName(String fileName, String path) {

	for (StringTokenizer fullPath = new StringTokenizer(path, File.pathSeparator) ; fullPath.hasMoreElements(); ) {
	    String nextPath = fullPath.nextToken() ;
	    String resolvedFileName = nextPath ;
	    File file = new File(resolvedFileName);
	    if ( file.exists())  {
		return resolvedFileName ;
	    }
	}
	// If no matching was found so far => return fileName
	return fileName ;
    }


}


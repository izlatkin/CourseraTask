/*
 * Copyright (c) 2003, Oracle and/or its affiliates. All rights reserved.
 */

package com.sun.management.mbeans.loading;

import java.io.*;

public class Serialization {

	// the first arg should be the class to be serialized,
	// the second is the file name to write the result.
	public static void main (String[] args) {
		try {
			for (int i=0; i<args.length; i+=2) {
				// create an object
				Class cl = Class.forName(args[i]);
				Object obj = (Object) cl.newInstance();

				// set properities
				if (obj instanceof FileSystemAccessor) {
//					System.out.println("\nSet state for FileSystemAccessor\n");
					((FileSystemAccessor)obj).setState("ToBeSerialized");
				}

				if (obj instanceof MLetBean) {
//					System.out.println("\nSet state for MLetBean\n");
					((MLetBean)obj).setState("ToBeSerialized");
					((MLetBean)obj).setInstanceName("MyMLetBean");
				}

				// open the file
				FileOutputStream ostream = new FileOutputStream(args[i+1]);
				ObjectOutputStream p = new ObjectOutputStream(ostream);

				// write
				p.writeObject(obj);
				p.flush();
				ostream.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

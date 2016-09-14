/*
 * Copyright (c) 2003, Oracle and/or its affiliates. All rights reserved.
 */

package com.sun.management.mbeans.loading ;

/**
 * This is the management interface of a class is used to test java native 
 * library loader supported by MLet.
 * <br>Both operations described herein use call to a native library.
**/


public interface RandomGenMBean  {
	
	public int getRandom();
 
 
}

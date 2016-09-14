/*
 * Copyright (c) 2003, Oracle and/or its affiliates. All rights reserved.
 */

package com.sun.management.mbeans.loading ;

/**
 * This is the management interface of the standard compliant 
 * MBean "MLetMultiCtors".
 * It offers for test purpose a game consisting on finding out a number 
 * randomly selected by the MBean at instantiation time.
 * The content of the interface is:
 * <ul>
 * <li> "isGreater(int nb)": returns true if nb > nb to find.
 * <li> "isLower(int nb)": returns true if nb > nb to find.
 * <li> "isEqual(int nb)": returns true if nb == nb to find.
 * <li>  RO attribute "Attempt": number of calls to the is* operations
 *       to find out the solution.
 * <li>  RW attribute "Limit": number to find belongs to the interval
 *       [0 .. Limit]
 * </ul>
 */
public interface MLetMultiCtorsMBean {

    public int getLimit();
    public void setLimit(int v);
	public int getAttempt();
    public boolean isGreater(int v);
    public boolean isLower(int v);
    public boolean isEqual(int v);
}

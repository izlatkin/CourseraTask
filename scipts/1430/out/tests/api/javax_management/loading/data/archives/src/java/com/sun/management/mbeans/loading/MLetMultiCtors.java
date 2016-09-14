/*
 * Copyright (c) 2003, Oracle and/or its affiliates. All rights reserved.
 */

package com.sun.management.mbeans.loading ;


// JDK import
import java.util.Vector;

/**
 * This is a JMX Compliant Standard MBean, offering several constructors of
 * various JDK basic types. This MBean is used for MLet loading tests.
 *
 * It implements a non-empty management interface providing a dummy game:
 * the rule is to find out a int number randomly choosen by the MBean.
 * The MBean exposes a protected default constructor + 9 public constructors.
 * <ul>
 * <li> "isGreater(int nb)": returns true if nb > nb to find.
 * <li> "isLower(int nb)": returns true if nb > nb to find.
 * <li> "isEqual(int nb)": returns true if nb == nb to find.
 * <li>  RO attribute "Attempt": number of calls to the is* operations
 *       to find out the solution.
 * <li>  RW attribute "Limit": number to find belongs to the interval
 *       [0 .. Limit]
 * <li> MLetMultiCtors():Choose a number into [0 .. 100]
 * <li> MLetMultiCtors(int limit): Set the Limit attribute and
 *      choose a number into [0 .. Limit]
 * <li> MLetMultiCtors(String limit): Set the Limit attribute and
 *      choose a number into [0 .. Limit]
 * <li> public MLetMultiCtors(MLetMultiCtors reference) : Sets the limit next 
 * to the reference limita dn chooses a random number accordingly.
 * <li> MLetMultiCtors(Vector vect): Set the Limit as the vector size.
 * <li> MLetMultiCtors(short) : a dummy public constructor.
* <li> MLetMultiCtors(String,Integer) : a dummy public constructor taking 2 
* jdk basic type arguments.
 * <li> MLetMultiCtors(double,int) : a dummy public constructor taking 2 args.
 * <li> MLetMultiCtors(float) : a dummy public constructor.
 * <li> MLetMultiCtors(int[]) : a dummy public constructor.
 * </ul>
 */
public class MLetMultiCtors implements MLetMultiCtorsMBean {

    /**
     * Choose a number between [0..limit]
     */
    public MLetMultiCtors(Integer limit) 
	throws RuntimeException {
	int l = limit.intValue();
	if(l < 0)
	    throw new RuntimeException("limit should be >=0");
	this.limit = l;
	this.nb = rnd(l + 1);
    }
    
    /**
     * Choose a number between [0..limit]
     */
    public MLetMultiCtors(String limit) 
	throws RuntimeException {
	
	int l = Integer.parseInt(limit);
	if(l < 0)
	    throw new RuntimeException("limit should be >=0");
	this.limit = l;
	this.nb = rnd(l + 1);
    }
    
    /**
     * Choose a number between [0..limit]. Where limit is the reference limit.
     */
    public MLetMultiCtors(MLetMultiCtors reference) {

	int l = reference.getLimit() ;
	
	this.limit = l;
	this.nb = rnd(l + 1);
    }

    /**
     * Protected default constructor. Allow to Choose a number between [0..100]
     */
    protected MLetMultiCtors() {
	this.nb = rnd(limit + 1);
    }

    /**
     * Dummy public constructor.
     */
    public MLetMultiCtors(Vector vect) {
	this.nb = vect.size() ;
    }
    
    /**
     * Dummy constructor taking 2 JDk type arguments.
     */
    public MLetMultiCtors(String limit, Integer nber ) {
	this.nb = 9 ;
    }

    /**
     * Dummy Public constructor taking 2 arguments.
     */
    public MLetMultiCtors(double v, int limit) {
	this.nb = 10;
    }
    
    /**
     * Dummy Public constructor.
     */
    public MLetMultiCtors(float v) {
	this.nb = 11;
    }
    
    /**
     * Dummy Public constructor.
     */
    public MLetMultiCtors(short v) {
	this.nb = 12;
    }

    /**
     * Dummy Public constructor.
     */
    public MLetMultiCtors(int[] tab) {
	
    }

    /**
     * Getter for the Limit Attribute.
     */
    public int getLimit() {
	return (limit);
    }
    
    /**
     * setter for the Limit Attribute.
     */
    public void setLimit(int v) 
	throws RuntimeException{
	if(limit < 0)
	    throw new RuntimeException("limit should be >=0");
	if(nbAttemps > 0)
	    throw new RuntimeException
		("game reset the limit when game has started");
	limit = v;
    }
    
    /**
     * getter for the Attempt Attribute.
     */
    public int getAttempt() {
	return (nbAttemps);
    }
    
    /**
     * isGreater() operation.
     */
    public boolean isGreater(int v) {
	if (!found)
	    nbAttemps++;
	return (v > nb);
    }
    
    /**
     * isLower() operation.
     */
    public boolean isLower(int v) {
	if (!found)
	    nbAttemps++;
	return (v < nb);
    }
    
    /**
     * isEqual() operation.
     */
    public boolean isEqual(int v) {
	if (!found)
	    nbAttemps++;
	if(v == nb)
	    found = true;
	return (v == nb);
    }
    
    
    /**
     * returns a value from [0..max[
     */
    private int rnd(int max) {
	return (int)(Math.random() * max);
    }
    
    private int nb;
    private int limit = 100;
    private int nbAttemps = 0;
    private boolean found = false;
}

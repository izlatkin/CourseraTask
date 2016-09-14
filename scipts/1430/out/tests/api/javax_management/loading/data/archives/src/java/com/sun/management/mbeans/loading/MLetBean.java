/*
 * Copyright (c) 2003, Oracle and/or its affiliates. All rights reserved.
 */

package com.sun.management.mbeans.loading ;

/**
 * This is a very simple mbean.
 * <br> It is used for tests related to JMX MLet service.
 **/

public class MLetBean implements java.io.Serializable, MLetBeanMBean {
	String State ;
	Integer NbChanges ;
	String InstanceName ;
	MLetBeanBis MLetBeanBis = null;


	public MLetBean() {
		InstanceName = "instanceInConstruction";
		State = "construct";
		NbChanges = new Integer(0);
	}

	public MLetBean(String state) {
		InstanceName = "instanceInConstruction";
		State = state ;
		NbChanges = new Integer(0);
	}


	public String getInstanceName() {
	    return (InstanceName);
	}

	public void setInstanceName(String s) {
		InstanceName = s;
		NbChanges = new Integer(NbChanges.intValue() +1);
	}

	public String getState() {
		return (State);
	}

	public void setState(String s) {
		State = s;
		NbChanges = new Integer(NbChanges.intValue() +1);
	}

	public MLetBeanBis getMLetBeanBis() {
		return (MLetBeanBis);
	}


	public void setMLetBeanBis(MLetBeanBis s) {
		MLetBeanBis = s;
		NbChanges = new Integer(NbChanges.intValue() +1);
        }

	public Integer getNbChanges() {
		return (NbChanges);
	}

	public void writeInstanceName() {

	}

}

 




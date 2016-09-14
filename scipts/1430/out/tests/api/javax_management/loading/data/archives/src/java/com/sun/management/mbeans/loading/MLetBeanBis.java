/*
 * Copyright (c) 2003, Oracle and/or its affiliates. All rights reserved.
 */

package com.sun.management.mbeans.loading ;

/**
 * This is a very simple mbean.
 * <br> It is used for tests related to JMX MLet service.
**/

public class MLetBeanBis implements java.io.Serializable, MLetBeanBisMBean {
	String StateBis ;
	String InstanceNameBis ;

	public MLetBeanBis() {
		InstanceNameBis = "instance of MLetBeanBis in construction";
		StateBis = "construct";
	}

	public MLetBeanBis(String state) {
		InstanceNameBis = "instance of MLetBeanBis in construction";
		StateBis = state ;
	}

	public String getInstanceNameBis() {
		return (InstanceNameBis);
	}

	public void setInstanceNameBis(String s) {
		InstanceNameBis = s;
	}

	public String getStateBis() {
		return (StateBis);
	}

	public void setStateBis(String s) {
		StateBis = s;
	}

}

 




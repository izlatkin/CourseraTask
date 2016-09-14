/*
 * Copyright (c) 2003, Oracle and/or its affiliates. All rights reserved.
 */

package com.sun.management.mbeans.loading ;

/**
 * This is a simple standard mbean used for tests related to JMX MLet service.
 **/

public class MLetBeanSimple implements java.io.Serializable, MLetBeanSimpleMBean {
	String State ;
	Integer NbChanges ;
	String InstanceName ;

    public MLetBeanSimple() {
	InstanceName = "instanceInConstruction";
	State = "construct";
	NbChanges = new Integer(0);
    }
    
    public MLetBeanSimple(String state) {
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
    
    public Integer getNbChanges() {
	return (NbChanges);
    }
    
    public String writeInstanceName() {
	return InstanceName ;
    }

}

 




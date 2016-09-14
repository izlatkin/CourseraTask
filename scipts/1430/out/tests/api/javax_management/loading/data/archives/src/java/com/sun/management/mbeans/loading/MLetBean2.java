/*
 * Copyright (c) 2003, Oracle and/or its affiliates. All rights reserved.
 */

package com.sun.management.mbeans.loading ;

/**
 * This is a simple standard mbean used for tests related to JMX MLet service.
 **/

public class MLetBean2 implements MLetBean2MBean , java.io.Serializable {
	String State ;
	Integer NbChanges ;
	String InstanceName ;
	MLetBeanBis MLetBeanBis = null;

    public MLetBean2() {
	InstanceName = "instanceInConstruction";
	State = "construct";
	NbChanges = new Integer(0);
    }

    public MLetBean2(String state) {
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
    
    public String writeInstanceState() {
	return State ;
    }
    
}

 




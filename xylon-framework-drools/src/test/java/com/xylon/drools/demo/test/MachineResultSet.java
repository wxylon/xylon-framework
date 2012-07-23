package com.xylon.drools.demo.test;

import com.xylon.drools.demo.Machine;


public interface MachineResultSet {
	
	boolean next();
	
	Machine getMachine();

}

package com.saintsrobotics.util;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

import edu.wpi.first.wpilibj.DriverStation;

public class ContinuousLog {

    private LinkedList<ContinuousLogObject> logs = new LinkedList<>();
    
    public void runLog(){
    	ListIterator<ContinuousLogObject> i = logs.listIterator();
    	ContinuousLogObject log = i.();
    	if(log.wantToLog()) DriverStation.reportError(log.getLogString(), false);
    	if(log.delete()) i.
    }
    public void logContinuous(ContinuousLogObject obj){
    	logs.addLast(obj);
    }
}

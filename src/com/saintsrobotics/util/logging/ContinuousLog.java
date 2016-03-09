package com.saintsrobotics.util.logging;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

import com.saintsrobotics.util.CircularQueue;

import edu.wpi.first.wpilibj.DriverStation;

public class ContinuousLog {
	
    private CircularQueue<ContinuousLogObject> logs = new CircularQueue<>();
    
    //Loop through all the logging objects in the queue, and check if they need to be logged/removed
    public void runLog(){
    	ContinuousLogObject logger = logs.next();
    	if(logger == null) return;
    	if(logger.wantToLog()) DriverStation.reportError(logger.getLogString(), false);
    	if(logger.delete()) logs.remove();
    }
    //add a logger to the queue.
    public void logContinuous(ContinuousLogObject obj){
    	logs.add(obj);
    }
}

package com.saintsrobotics.util.logging;

import com.saintsrobotics.Robot;

public class TimeIntervalLogger implements ContinuousLogObject {
	//When is the next time we need to log?
	private long hitTime = 0;
	//Should we request deletion?
	public boolean delete = false;
	
	//Time between each log
	public long intervalTime;
	//What to log
	private String value;
	//Seconds to nanoseconds
	private long secToNano(double sec){return (long)(sec * 1000000000);}
	
	//Construct with a default delay of the interview
	public TimeIntervalLogger(double intervalTime){
		this(intervalTime,intervalTime);
	}
	//Construct a logger that logs every intervalTime, starting after a time of firstDelay. 
	public TimeIntervalLogger(double intervalTime, double firstDelay){
		this.intervalTime = secToNano(intervalTime) ;
		hitTime = System.nanoTime() + secToNano(firstDelay);
		Robot.log.logContinuous(this);
	}
	//Set the value to be logged.
	public void setValue(Object toLog){
		value = toLog.toString();
	}
	@Override
	public String getLogString() {
		return value;
	}
	
	@Override
	public boolean wantToLog() {
		//If we're logging, shift the next time to log to intervalTime nanoseconds later
		if(System.nanoTime() > hitTime){
			hitTime = System.nanoTime() + intervalTime;
			return true;
		}
		return false;
	}
	

	@Override
	public boolean delete() {
		return delete;
	}

}

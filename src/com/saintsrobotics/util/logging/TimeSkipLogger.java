package com.saintsrobotics.util.logging;

public class TimeSkipLogger implements ContinuousLogObject {
	public long hitTime = 0;
	public boolean delete = false;
	
	public long intervalTime;
	private String value;
	private long secToNano(double sec){return (long)(sec * 1000000000);}
	
	public TimeSkipLogger(double intervalTime){
		this.intervalTime = secToNano(intervalTime);
		hitTime = System.nanoTime() + this.intervalTime;
	}
	public TimeSkipLogger(double intervalTime, double firstDelay){
		this.intervalTime = secToNano(intervalTime) ;
		hitTime = System.nanoTime() + secToNano(firstDelay);
	}
	public void setValue(Object toLog){
		value = toLog.toString();
	}
	@Override
	public String getLogString() {
		return value;
	}

	@Override
	public boolean wantToLog() {
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

package com.saintsrobotics.util.logging;

public interface ContinuousLogObject {
	public String getLogString();
	public boolean wantToLog();
	public boolean delete();
}

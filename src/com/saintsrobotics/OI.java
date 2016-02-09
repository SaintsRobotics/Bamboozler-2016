package com.saintsrobotics;

import edu.wpi.first.wpilibj.Joystick;

public class OI {
	
	public enum Axis {
		LX(0),
    	LY(1),
    	RX(2),
    	RY(3),
    	LT(4),
    	RT(5);
    	
    	int rawIndex;
    	
    	Axis(int rawIndex) {
    		this.rawIndex = rawIndex;
    	}
	}
	
    public enum Button {
    	A(0),
    	B(1),
    	X(2),
    	Y(3),
    	RB(4),
    	LB(5),
    	START(6),
    	SELECT(7),
    	R3(8),
    	L3(9);
    	
    	int rawIndex;
    	
    	Button(int rawIndex) {
    		this.rawIndex = rawIndex;
    	}
    }
	
	private Joystick stick;
	
	public OI() {
		stick = new Joystick(0);
	}
	
	public boolean getButton(Button button) {
		return stick.getRawButton(button.rawIndex);
	}
	
	public double getAxis(Axis axis) {
		return stick.getRawAxis(axis.rawIndex);
	}
}

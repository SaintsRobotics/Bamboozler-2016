package com.saintsrobotics;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;

public enum Motors {
	
	DRIVE_LEFT_1(new Talon(0), false),
	DRIVE_LEFT_2(new Talon(1), false),
	DRIVE_RIGHT_1(new Talon(2), false),
	DRIVE_RIGHT_2(new Talon(3), false),
	PICKUP(new Talon(4), false),
	MOTOR_6(new Talon(5), false),
	MOTOR_7(new Talon(6), false),
	MOTOR_8(new Talon(7), false);
	
	private SpeedController motor;
	
	Motors(SpeedController motor, boolean inverted) {
		this.motor = motor;
		motor.setInverted(inverted);
	}
	
	public double get() {
		return motor.get();
	}
	
	public void set(double speed) {
		motor.set(speed);
	}
}

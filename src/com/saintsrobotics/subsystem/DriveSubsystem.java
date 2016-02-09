package com.saintsrobotics.subsystem;

import com.saintsrobotics.Motors;

public class DriveSubsystem {
	
	public void driveArcade(double speed, double turn) {
		driveTank(speed+turn, speed-turn);
	}
	
	public void driveTank(double left, double right) {
		Motors.DRIVE_LEFT_1.set(left);
		Motors.DRIVE_LEFT_2.set(left);
		Motors.DRIVE_RIGHT_1.set(right);
		Motors.DRIVE_RIGHT_2.set(right);
	}
}

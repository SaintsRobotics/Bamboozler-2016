package com.saintsrobotics.subsystem;

import com.saintsrobotics.Motors;

public class DriveSubsystem {
	//Base speed multiplier
    public double multiplier = 1;
    public double turnMultiplier = 1;

    public DriveSubsystem() {
        this(1);
    }

    public DriveSubsystem(double mult) {
        this(mult, mult);
    }

    public DriveSubsystem(double mult, double turnMult) {
        multiplier = mult;
        turnMultiplier = turnMult;
    }

    public void driveArcade(double speed, double turn) {
        speed *= multiplier;
        turn *= turnMultiplier;
        drive(speed + turn, speed - turn);
    }
    public void driveTank(double left, double right){
    	drive(multiplier * left, multiplier * right);
    }
    private void drive(double left, double right) {
        Motors.DRIVE_LEFT_1.set(left);
        Motors.DRIVE_LEFT_2.set(right);
        Motors.DRIVE_RIGHT_1.set(left);
        Motors.DRIVE_RIGHT_2.set(right);
    }
}

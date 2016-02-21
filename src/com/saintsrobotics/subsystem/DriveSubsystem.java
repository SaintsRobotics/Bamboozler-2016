package com.saintsrobotics.subsystem;

import com.saintsrobotics.Motors;

public class DriveSubsystem {

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
        drive(speed - turn, speed + turn);
    }

    public void drive(double left, double right) {
        Motors.DRIVE_LEFT_1.set(left);
        Motors.DRIVE_LEFT_2.set(left);
        Motors.DRIVE_RIGHT_1.set(right);
        Motors.DRIVE_RIGHT_2.set(right);
    }
}

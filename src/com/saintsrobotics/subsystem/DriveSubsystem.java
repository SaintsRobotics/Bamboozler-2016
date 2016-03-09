package com.saintsrobotics.subsystem;

import com.saintsrobotics.Robot;

public class DriveSubsystem {

    public double forwardMult = 1;
    public double turnMult = 1;

    public DriveSubsystem() {
        this(1);
    }

    public DriveSubsystem(double mult) {
        this(mult, mult);
    }

    public DriveSubsystem(double forwardMult, double turnMult) {
        this.forwardMult = forwardMult;
        this.turnMult = turnMult;
    }

    public void driveArcade(double speed, double turn) {
        speed *= forwardMult;
        turn *= turnMult;
        drive(speed - turn, speed + turn);
    }

    public void driveTank(double left, double right){
        drive(forwardMult * left, forwardMult * right);
    }

    private void drive(double left, double right) {
        Robot.MOTORS.DRIVE_LEFT_1().set(left);
        Robot.MOTORS.DRIVE_LEFT_2().set(left);
        Robot.MOTORS.DRIVE_LEFT_3().set(left);
        Robot.MOTORS.DRIVE_RIGHT_1().set(right);
        Robot.MOTORS.DRIVE_RIGHT_2().set(right);
        Robot.MOTORS.DRIVE_RIGHT_3().set(right);
    }
}

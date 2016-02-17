package com.saintsrobotics;

import com.saintsrobotics.OI.Axis;
import com.saintsrobotics.subsystem.ArmSubsystem;
import com.saintsrobotics.subsystem.DriveSubsystem;
import com.saintsrobotics.subsystem.PickupSubsystem;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SampleRobot;

public class Robot extends SampleRobot {

    OI oi = new OI();
    DriveSubsystem drive = new DriveSubsystem();
    PickupSubsystem pickup = new PickupSubsystem(0.3);
    ArmSubsystem arm = new ArmSubsystem();

    public void robotInit() {
    	drive.multiplier = 1;
    }

    public void autonomous() {

    }

    public void operatorControl() {
        while (isOperatorControl() && isEnabled()) {
//            drive.driveArcade(oi.getAxis(OI.Axis.LY), oi.getAxis(OI.Axis.RX));
            arm.setArmThing(oi.getAxis(Axis.RY) * 0.3);
            arm.setWinch(oi.getAxis(Axis.LY) * 0.3);
//            pickup.rotate(oi.getAxis(Axis.RY));
        }
    }
    
    public static void log(String message) {
        DriverStation.reportError(message + "\n", false);
    }
}

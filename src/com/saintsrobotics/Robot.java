package com.saintsrobotics;

import com.saintsrobotics.subsystem.DriveSubsystem;
import com.saintsrobotics.subsystem.PickupSubsystem;

import edu.wpi.first.wpilibj.SampleRobot;

public class Robot extends SampleRobot {

    PickupSubsystem pickup = new PickupSubsystem();
    DriveSubsystem drive = new DriveSubsystem();
    OI oi = new OI();

    public Robot() {

    }

    public void robotInit() {

    }

    public void autonomous() {

    }

    public void operatorControl() {
        drive.driveTank(oi.getAxis(OI.Axis.LY), oi.getAxis(OI.Axis.RX));
        pickup.rotate(oi.getAxis(OI.Axis.RT) - oi.getAxis(OI.Axis.LT));
    }
}

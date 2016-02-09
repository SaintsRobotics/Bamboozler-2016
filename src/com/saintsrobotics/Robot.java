package com.saintsrobotics;

import com.saintsrobotics.subsystem.DriveSubsystem;

import edu.wpi.first.wpilibj.SampleRobot;

public class Robot extends SampleRobot {
	
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
    }
}

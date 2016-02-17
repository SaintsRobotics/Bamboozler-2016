package com.saintsrobotics;

import com.saintsrobotics.OI.Axis;
import com.saintsrobotics.subsystem.*;


import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class Robot extends SampleRobot {

    OI oi = new OI();
    DriveSubsystem drive = new DriveSubsystem();
    PickupSubsystem pickup = new PickupSubsystem(0.3);
    ArmSubsystem arm = new ArmSubsystem();
    ChooChooSubsystem choochoo = new ChooChooSubsystem();
    public Robot() {

    }

    public void robotInit() {
    	drive.multiplier = 1;
    	//Use the livewindow for PID tuning
    	LiveWindow.addActuator("pickup", "Pickup PID Controller", pickup.pid);

    }

    public void autonomous() {

    }

    public void operatorControl() {
    	Robot.log("hit Teleop");
        while (isOperatorControl() && isEnabled()) {
            drive.driveArcade(oi.getAxis(OI.Axis.LY), oi.getAxis(OI.Axis.RX));
            arm.setArmThing(oi.getOpAxis(Axis.LY));
            arm.setWinch(oi.getOpAxis(Axis.RY));
            pickup.rotate(oi.getAxis(Axis.LT) - oi.getAxis(Axis.RT));
        	if(oi.getButton(OI.Button.A)){
        		//Run chochoo, output current state. replace log call with something more elegant later.
        		log(choochoo.brakakaka().toString());
        	}
        	
        }
    }
    public void test(){
    	log("Hit Test");
    	LiveWindow.setEnabled(true);
    	while(isTest()&&isEnabled())
    		LiveWindow.run();
    }
    public static void log(String message){
		DriverStation.reportError(message + "\n",false);
    }
}

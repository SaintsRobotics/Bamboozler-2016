package com.saintsrobotics;

import com.saintsrobotics.OI.Axis;
import com.saintsrobotics.subsystem.ArmSubsystem;
import com.saintsrobotics.subsystem.DriveSubsystem;
import com.saintsrobotics.subsystem.PickupSubsystem;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class Robot extends SampleRobot {

    OI oi = new OI();
    DriveSubsystem drive = new DriveSubsystem();
    PickupSubsystem pickup = new PickupSubsystem(0.3);
    ArmSubsystem arm = new ArmSubsystem();
    public Robot() {

    }

    public void robotInit() {
    	drive.multiplier = 1;
    }

    public void autonomous() {

    }

    public void operatorControl() {
        while (isOperatorControl() && isEnabled()) {
            drive.driveArcade(oi.getAxis(OI.Axis.LY), oi.getAxis(OI.Axis.RX));
            arm.setArmThing(oi.getOpAxis(Axis.LY));
            arm.setWinch(oi.getOpAxis(Axis.RY));
            pickup.rotate(oi.getAxis(Axis.LT) - oi.getAxis(Axis.RT));
        }
        
    }
    public void test(){
    	LiveWindow.setEnabled(true);
    	LiveWindow.addActuator("pickup", "Pickup PID Controller", pickup.pid);
    }
    public static void log(String message){
    	DriverStation.getInstance().reportError(message + "\n",false);
    }
}

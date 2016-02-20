package com.saintsrobotics;

import com.saintsrobotics.OI.Axis;
import com.saintsrobotics.OI.Button;
import com.saintsrobotics.subsystem.*;
import com.saintsrobotics.subsystem.ChooChooSubsystem.Stage;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.tables.IRemote;
import edu.wpi.first.wpilibj.tables.IRemoteConnectionListener;
import edu.wpi.first.wpilibj.tables.ITable;
import edu.wpi.first.wpilibj.tables.ITableListener;

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

    	SmartDashboard.putData("Arm Pid", arm.armPid);
    	SmartDashboard.putData("Winch Pid", arm.winchPid);
    }

    public void autonomous() {

    }

    public void operatorControl() {
    	Robot.log("hit Teleop");
        while (isOperatorControl() && isEnabled()) {
            //drive.driveArcade(oi.getAxis(OI.Axis.LY), oi.getAxis(OI.Axis.RX));
            //arm.setArmThing(oi.getOpAxis(Axis.LY));
            //arm.setWinch(oi.getOpAxis(Axis.RY));
            //pickup.rotate(oi.getAxis(Axis.LT) - oi.getAxis(Axis.RT));
        	if(oi.getButton(OI.Button.A)){
        		//Run chochoo, output current state. replace log call with something more elegant later.
        		Stage s = choochoo.brakakaka();
        		if(s!=Stage.RUNNING){
        			log(s.toString());
        		}
        	}else{
        		
        	}
        	if(oi.getButton(OI.Button.B)){
        		log("Current Motor Speed :" + Motors.CHOOCHOO.get());
        	}
        	//arm.setArmThing(oi.getAxis(Axis.LY));
        	//arm.setWinch(oi.getAxis(Axis.RY));
        }
    }
    public void test(){
    	NetworkTable table = NetworkTable.getTable("pidtune");
    	
    	table.addConnectionListener(new IRemoteConnectionListener(){

			@Override
			public void connected(IRemote arg0) {
				// TODO Auto-generated method stub
				log("connected");
			}

			@Override
			public void disconnected(IRemote arg0) {
				// TODO Auto-generated method stub
				log("disconnected");
			}
			
		}, true);

    	log("Hit Test");
    	log("Is server: " + table.isServer());
    	//LiveWindow.addActuator("PID", "pickupPid", pickup.pid);
    	
    	//LiveWindow.addSensor("Arms", "arm", Sensor.Potentiometer.ARM.getRawPot());
    	//LiveWindow.addSensor("Arms", "winch", Sensor.Potentiometer.WINCH.getRawPot());
    	LiveWindow.setEnabled(false);
    	if(isTest()&&isEnabled()){
    		pickup.pid.disable();
    		arm.armPid.disable();
    		arm.winchPid.disable();
    		arm.setArmThing(0);
    		arm.setWinch(1);
    		choochoo.backDrive();
    		//log("Arm Pos: " + Sensor.Potentiometer.ARM.get());
    		//log("Winch Pos: " + Sensor.Potentiometer.WINCH.get());
    		//log("Current Distance: " + arm.calculateDistance());
    	}
    	while(isTest()&&isEnabled()){
    		//pickup.dangerousSet(oi.getAxis(Axis.LY));
    		//arm.setArmThing(oi.getAxis(Axis.LY));
    		//arm.setWinch(oi.getAxis(Axis.RY));
    		
    		log("Setpoint: " + arm.winchPid.getSetpoint());
    		log("Current Output: " + arm.winchPid.get());
    		log("Current Encoder Value: " + Sensor.Potentiometer.WINCH.get());
    	}
    }
    public static void log(String message){
		DriverStation.reportError(message + "\n",false);
    }
}

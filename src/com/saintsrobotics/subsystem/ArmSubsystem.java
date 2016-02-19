package com.saintsrobotics.subsystem;

import com.saintsrobotics.Motors;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.saintsrobotics.Sensor;
public class ArmSubsystem {
	public PIDController winchPid = new PIDController(1d, 0d, 0d, Sensor.Potentiometer.WINCH.getRawPot(), Motors.ARM_WINCH.getRawMotor());
	public PIDController armPid = new PIDController(1d, 0d, 0d, Sensor.Potentiometer.ARM.getRawPot(), Motors.ARM_AXLE.getRawMotor());
	public void setWinch(double pos){
		
		winchPid.setPID(SmartDashboard.getNumber("winchP",1d), SmartDashboard.getNumber("winchI", 0d), 0);
		winchPid.setSetpoint(pos);
	}
	public void setArmThing(double pos){
		armPid.setPID(SmartDashboard.getNumber("armP",1d), SmartDashboard.getNumber("armI", 0d), 0);
		armPid.setSetpoint(pos);
	}
	private double armLength = 0;
	public double calculateDistance(){
		return calculateDistance(Sensor.Potentiometer.WINCH.get(),Sensor.Potentiometer.ARM.get());
	}
	public double calculateDistance(double winchAngle, double armAngle){
		return armLength * Math.sin(armAngle) * Math.cos(winchAngle);
	}
}

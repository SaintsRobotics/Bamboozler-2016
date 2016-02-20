package com.saintsrobotics.subsystem;

import com.saintsrobotics.Motors;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.saintsrobotics.Sensor;
public class ArmSubsystem {
	public PIDController winchPid = new PIDController(1.2d, 0.5d, 0d, Sensor.Potentiometer.WINCH.getRawPot(), Motors.ARM_WINCH.getRawMotor());
	public PIDController armPid = new PIDController(1.5d, 0.8d, 0d, Sensor.Potentiometer.ARM.getRawPot(), Motors.ARM_AXLE.getRawMotor());
	
	NetworkTable table = NetworkTable.getTable("pidtune");

	public ArmSubsystem(){
		winchPid.setAbsoluteTolerance(0.1);
		armPid.setAbsoluteTolerance(0.1);
	}
	public void setWinch(double pos){
		
		winchPid.setSetpoint(pos);
	}
	public void setArmThing(double pos){
		armPid.setSetpoint(pos);
	}
	private double armLength = 0;
	public double calculateDistance(){
		return calculateDistance(Sensor.Potentiometer.WINCH.get(),Sensor.Potentiometer.ARM.get());
	}
	public double calculateDistance(double winchAngle, double armAngle){
		return armLength * Math.sin(armAngle) * Math.cos(winchAngle);
	}
	public void runTest(){
		winchPid.setPID(Double.parseDouble(table.getString("winchPIDp", "1")), Double.parseDouble(table.getString("winchPIDi", "1")), 0);
		armPid.setPID(Double.parseDouble(table.getString("armPIDp", "1")), Double.parseDouble(table.getString("armPIDi", "1")), 0);
		winchPid.setSetpoint(Double.parseDouble(table.getString("winchPIDset", "0")));
		armPid.setSetpoint(Double.parseDouble(table.getString("armPIDset", "0")));
		table.putString("outputWinch", "" + winchPid.get());
		table.putString("outputArm", "" + armPid.get());
		table.putString("posWinch",  "" + Sensor.Potentiometer.WINCH.get());
		table.putString("posWinch", "" + Sensor.Potentiometer.ARM.get());
	}
}

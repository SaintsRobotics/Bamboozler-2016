package com.saintsrobotics.subsystem;

import com.saintsrobotics.Motors;
import com.saintsrobotics.Robot;
import com.saintsrobotics.Sensor;

import edu.wpi.first.wpilibj.PIDController;

public class PickupSubsystem {
	
	public double mult;
	public boolean set = false;
	public double rate = 0;
	public PIDController pid;
	public PickupSubsystem (){
		this(1);
	}
	public PickupSubsystem(double mult){
		this.mult = mult;
		this.set = false;
		this.pid = new PIDController(0.6d, 0.05d, 0d, Sensor.Encoders.PICKUP.getRawEncoder(), Motors.PICKUP.getRawMotor());
		this.pid.setAbsoluteTolerance(0.5);
	}
	public void set(double speed){
		
		speed*=5;
		if(set){
			if (speed > 0d) {
				pid.setSetpoint(0);
				return;
			}
			pid.setSetpoint(speed);
		}else{
			if(Sensor.LimitSwitches.PICKUP.get()){
				Sensor.Encoders.PICKUP.zero();
				Robot.log("set");
				set = true;
			}
		}
	}
	public void dangerousSet(double set){
		pid.setSetpoint(set* 8);
	}
	public void rotate(double speed) {
		Motors.PICKUP.set(speed);
	}
}

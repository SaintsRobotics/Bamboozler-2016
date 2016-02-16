package com.saintsrobotics.subsystem;

import com.saintsrobotics.Motors;
import com.saintsrobotics.Sensor;
public class ArmSubsystem {
	public void setWinch(double speed){
		Motors.ARM_WINCH.set(speed );
	}
	public void setArmThing(double speed){
		speed=-speed;
		if(speed < 0 && Sensor.LimitSwitches.ARM.get()){
			speed = 0;
		}
		Motors.ARM_AXLE.set(speed );
	}
}

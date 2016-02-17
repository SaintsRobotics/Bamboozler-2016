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
		this.pid = new PIDController(1d, 0d, 0d, Sensor.Encoders.PICKUP.getRawEncoder(), Motors.PICKUP.getRawMotor());
	}
	
	public void rotate(double speed) {
		if(set){
			if (speed > 0d && Sensor.Encoders.PICKUP.get() > -60d) {
				pid.setSetpoint(0);
				return;
			} else if (speed < 0d && Sensor.Encoders.PICKUP.get() < -290d /*found by trial and error*/) {
				pid.setSetpoint(0);
				return;
			}
		}else{
			if(Sensor.LimitSwitches.PICKUP.get()){
				Sensor.Encoders.PICKUP.zero();
				Robot.log("set");
				set = true;
			}
		}
		pid.setSetpoint(Sensor.Encoders.PICKUP.get() + speed * mult);
	}
}

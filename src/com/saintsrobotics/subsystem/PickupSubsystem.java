package com.saintsrobotics.subsystem;

import com.saintsrobotics.Motors;
import com.saintsrobotics.Robot;
import com.saintsrobotics.Sensor;

import edu.wpi.first.wpilibj.DriverStation;

public class PickupSubsystem {
	
	public double mult;
	public boolean set = false;
	public double rate = 0;
	public PickupSubsystem (){
		this(1);
	}
	public PickupSubsystem(double mult){
		this.mult = mult;
		this.set = false;
	}
	
	public void rotate(double speed) {
		if(set){
			if (speed > 0d && Sensor.Encoders.PICKUP.get() > -60d) {
				Motors.PICKUP.set(-0.1);
				return;
			} else if (speed < 0d && Sensor.Encoders.PICKUP.get() < -290d /*found by trial and error*/) {
				Motors.PICKUP.set(0.1);
				return;
			}
		}else{
			if(Sensor.LimitSwitches.PICKUP.get()){
				Sensor.Encoders.PICKUP.zero();
				Robot.log("set");
				set = true;
			}
		}
		Motors.PICKUP.set(speed * mult);
		/*if(Sensor.Encoders.PICKUP.getRate() == 0) return;
		if(speed/Sensor.Encoders.PICKUP.getRate()!=rate){
			Robot.log("ratio = " + speed/Sensor.Encoders.PICKUP.getRate());
			rate = speed/Sensor.Encoders.PICKUP.getRate();
		}*/
		
	}
}

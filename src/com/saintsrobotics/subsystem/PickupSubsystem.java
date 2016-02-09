package com.saintsrobotics.subsystem;

import com.saintsrobotics.Motors;
import com.saintsrobotics.Sensor;

public class PickupSubsystem {
	
	public void rotate(double speed) {
		if (speed < 0 && Sensor.LimitSwitches.PICKUP_BOTTOM.isDown()) {
			speed = 0;
		} else if (speed > 0 && Sensor.LimitSwitches.PICKUP_TOP.isDown()) {
			speed = 0;
		}
		Motors.PICKUP.set(speed);
	}
}

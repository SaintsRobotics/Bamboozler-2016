package com.saintsrobotics;

import edu.wpi.first.wpilibj.DigitalInput;

public class Sensor {
	
	public enum LimitSwitches {
		
		PICKUP_TOP(new DigitalInput(0), false),
		PICKUP_BOTTOM(new DigitalInput(1), false);
		
		private DigitalInput limitSwitch;
		private boolean inverted;
		
		LimitSwitches(DigitalInput limitSwitch, boolean inverted) {
			this.limitSwitch = limitSwitch;
			this.inverted = inverted;
		}
		
		public boolean isDown() {
			return limitSwitch.get() ^ inverted;
		}
	}
}

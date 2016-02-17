package com.saintsrobotics;

import edu.wpi.first.wpilibj.CounterBase;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;

public class Sensor {
    
	public enum Encoders {
	    
		PICKUP(0, false, 1, 1, 0d),
		WINCH(4, false, 7, 188, 0d),
		OTHERONE(2, false, 7, 71, 0d);
		
		Encoder encoder;
		
		Encoders(int pin, boolean inverted, int pulsePerRev, int gearBoxRate, double minRate){
			this(pin, inverted, pulsePerRev * gearBoxRate, minRate);
		}
		
		Encoders(int pin, boolean inverted, double distancePerPulse, double minRate){
			encoder = new Encoder(pin, pin+1, inverted, CounterBase.EncodingType.k2X);
			encoder.setDistancePerPulse(distancePerPulse);
			encoder.setMinRate(minRate);
			encoder.setSamplesToAverage(10);
		}
		
		public double get() {
			return encoder.getDistance();
		}
		
		public double getRaw() {
			return encoder.getRaw();
		}
		
		public void zero() {
			encoder.reset();
		}
		
		public double getRate() {
			return encoder.getRate();
		}
	}
	
	public enum LimitSwitches {
	    
		PICKUP(9),
		ARM(8);
	    
		private DigitalInput limit;
		
		LimitSwitches(int pin) {
			limit = new DigitalInput(pin);
		}
		
		public boolean get() {
			return !limit.get();
		}
	}
}

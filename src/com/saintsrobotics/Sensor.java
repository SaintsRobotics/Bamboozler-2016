package com.saintsrobotics;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.CounterBase;
public class Sensor {
	public enum Encoders{
		PICKUP(0,false,1, 71,0d),
		WINCH(4,false,7, 188,0d),
		ARM(2,false,7,71,0d);
		
		Encoder encoder;
		Encoders(int pin, boolean inverted,int pulsePerRev, int gearBoxRate, double minRate){
			this(pin, inverted, pulsePerRev * gearBoxRate, minRate);
		}
		Encoders(int pin, boolean inverted, double distancePerPulse, double minRate){
			encoder = new Encoder(pin, pin+1, inverted, CounterBase.EncodingType.k2X);
			encoder.setDistancePerPulse(1/distancePerPulse);
			encoder.setMinRate(minRate);
			encoder.setSamplesToAverage(10);
		}
		public double get(){
			return encoder.getDistance();
		}
		public double getRaw(){
			return encoder.getRaw();
		}
		public void zero(){
			encoder.reset();
		}
		public double getRate(){
			return encoder.getRate();
		}
		public Encoder getRawEncoder(){
			return encoder;
		}
	}
	public enum Potentiometer{
		WINCH(0,6.283,-3.84),
		ARM(1,6.283,-0.098);
		AnalogPotentiometer pot;
		Potentiometer(int port,double fullrange, double offset){
			pot = new AnalogPotentiometer(port,fullrange,offset);
		}
		public double get(){
			
			return pot.get();
		}
		public AnalogPotentiometer getRawPot(){
			return pot;
		}
	}
	public enum LimitSwitches{
		PICKUP(9),
		ARM(8),
		CHOOCHOO(7);
		DigitalInput limit;
		LimitSwitches(int pin){
			limit = new DigitalInput(pin);
		}
		public boolean get(){
			return !limit.get();
		}
	}
}

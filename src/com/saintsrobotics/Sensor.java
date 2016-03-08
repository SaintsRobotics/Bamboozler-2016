package com.saintsrobotics;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.CounterBase;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;

public class Sensor {

    public enum Encoders {
        PICKUP(0, false, 1, 71, 0d);

        private Encoder encoder;

        Encoders(int pin, boolean inverted, int pulsesPerRev, int gearBoxRate, double minRate) {
            this(pin, inverted, pulsesPerRev * gearBoxRate, minRate);
        }

        Encoders(int pin, boolean inverted, double distancePerPulse, double minRate) {
        	encoder = new Encoder(pin, pin+1, inverted);
            encoder.setDistancePerPulse(1 / distancePerPulse);
            encoder.setMinRate(minRate);
            encoder.setSamplesToAverage(10);
        }

        public double get() {
            return encoder.getDistance();
        }

        public void zero() {
            encoder.reset();
        }
    }

     public enum Potentiometer {

        ELBOW(1, 270, -2.6857),
        ARM(0, 270, -14.7425);

        private AnalogPotentiometer pot;

        Potentiometer(int port, double fullrange, double offset) {
            pot = new AnalogPotentiometer(port, fullrange, offset);
        }

        public double get() {
            return pot.get();
        }
    }

    public enum LimitSwitches {
        
        PICKUP(9),
        ARM(8),
        CHOOCHOO(7);

        private DigitalInput limit;

        LimitSwitches(int pin) {
            limit = new DigitalInput(pin);
        }

        public boolean get() {
            return !limit.get();
        }
    }
}

package com.saintsrobotics.subsystem;

import com.saintsrobotics.Constants;
import com.saintsrobotics.Robot;
import com.saintsrobotics.Sensor;
import com.saintsrobotics.util.PID;
import com.saintsrobotics.util.logging.TimeIntervalLogger;

public class PickupSubsystem {

    // if the pickup has gone all the way up into the limit switch to zero the encoder
    private boolean set = false;
    private PID pickupPid = new PID(20, 0, 0);

    TimeIntervalLogger log = new TimeIntervalLogger(1);

    public boolean isSet() {
        return set;
    }
    
    // Encoder: 0 -> -10.2, full up -> full down
    public void set(double pos) {
        if (pos > 1) pos = 1;

        if (Sensor.LimitSwitches.PICKUP.get()) {
            Sensor.Encoders.PICKUP.zero();
            set = true;
        }
        
        log.setValue("pos: " + pos +"\nSet : " + set);

        if (set) {
            double val = pickupPid.compute(Sensor.Encoders.PICKUP.get()/Constants.PICKUP_ENCODER_RANGE, pos);
//            log.log("[ " + ((int)(val*1000))/1000d
//                    + " " + Sensor.Encoders.PICKUP.get()
//                    + " " + (int)(pos*1000)/1000d);
            Robot.MOTORS.PICKUP().set(val);
        } else {
            Robot.MOTORS.PICKUP().set(0.2);
        }
    }
}

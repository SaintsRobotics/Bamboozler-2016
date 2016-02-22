package com.saintsrobotics.subsystem;

import com.saintsrobotics.Motors;
import com.saintsrobotics.Robot;
import com.saintsrobotics.Sensor;
import com.saintsrobotics.util.PID;

public class PickupSubsystem {

    // if the pickup has gone all the way up into the limit switch to zero the encoder
    private boolean set = true;
    private PID pickupPid = new PID(20, 0, 0);
    
    int cnt;

    public PickupSubsystem() {
        this.set = false;
    }
    
    // Encoder: 0 -> 10.2, full up -> full down
    public void set(double pos) {
        if (Sensor.LimitSwitches.PICKUP.get()) {
            Sensor.Encoders.PICKUP.zero();
            set = true;
        }
        if (set) {
            double val = pickupPid.compute(Sensor.Encoders.PICKUP.get()/10.2, pos);
            Motors.PICKUP.set(val);
            if (cnt++ % 100 == 0) Robot.log("[ " + ((int)(val*1000))/1000d
            		+ " " + Sensor.Encoders.PICKUP.get()
            		+ " " + (int)(pos*1000)/1000d);
        } else {
            Motors.PICKUP.set(0.1);
        }
    }
    
    // makes the pickup go up and zero the encoder
    public void zero() {
        set = false;
    }
}

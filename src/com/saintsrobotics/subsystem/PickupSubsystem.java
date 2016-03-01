package com.saintsrobotics.subsystem;

import com.saintsrobotics.Motors;
import com.saintsrobotics.Robot;
import com.saintsrobotics.Sensor;
import com.saintsrobotics.util.PID;

public class PickupSubsystem {

    // if the pickup has gone all the way up into the limit switch to zero the encoder
    private boolean set;
    private PID pickupPid = new PID(20, 0, 0);
    
    int cnt;

    
    // Encoder: 0 -> -10.2, full up -> full down
    public void set(double pos) {
        if (Sensor.LimitSwitches.PICKUP.get()) {
            Sensor.Encoders.PICKUP.zero();
            set = true;
        }
        if(pos < 0) pos = 0; else if (pos > 1) pos = 1;
        if (set) {
            double val = pickupPid.compute(Sensor.Encoders.PICKUP.get()/10.2, -pos);
            
            
            if (Robot.debug && cnt++ % 10000 == 0) Robot.log("[ " + ((int)(val*1000))/1000d
            		+ " " + Sensor.Encoders.PICKUP.get()
            		+ " " + (int)(pos*1000)/1000d);
            else
            	Motors.PICKUP.set(val);
        } else {
            Motors.PICKUP.set(0.3);
        }
    }
    
    // makes the pickup go up and zero the encoder
    public void zero() {
        set = false;
    }
}

package com.saintsrobotics.subsystem;

import com.saintsrobotics.Motors;
import com.saintsrobotics.Sensor;

import edu.wpi.first.wpilibj.Timer;

public class ChooChooSubsystem {
    
    private Timer timer = new Timer();
    private boolean firing = false;
    
    public void wind() {
        if (!Sensor.LimitSwitches.CHOOCHOO.get() || firing) {
            Motors.CHOOCHOO.set(1);
        } else {
            Motors.CHOOCHOO.set(0);
        }
        if (timer.hasPeriodPassed(0.5)) {
            firing = false;
            timer.stop();
        }
    }

    public void fire() {
        if (Sensor.LimitSwitches.CHOOCHOO.get() && !firing) {
            firing = true;
            timer.start();
        }
    }
}

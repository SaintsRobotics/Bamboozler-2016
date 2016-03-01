package com.saintsrobotics.subsystem;

import com.saintsrobotics.Motors;
import com.saintsrobotics.Sensor;

import edu.wpi.first.wpilibj.Timer;

public class ChooChooSubsystem {
    //Timer to only time winding
    private Timer timer = new Timer();
    //Are we firing? this variable tells us. If it isn't lying.
    private boolean firing = false;
    public void wind(){
    	wind(1);
    }
    public void wind(double speed) {
        if (!Sensor.LimitSwitches.CHOOCHOO.get() || firing) {
            Motors.CHOOCHOO.set(speed);
        } else {
            Motors.CHOOCHOO.set(0);
        }
        if (timer.hasPeriodPassed(0.5)) {
            firing = false;
            timer.stop();
            timer.reset();
        }
    }

    public void fire() {
        if (Sensor.LimitSwitches.CHOOCHOO.get() && !firing) {
            firing = true;
            timer.start();
        }
    }
}

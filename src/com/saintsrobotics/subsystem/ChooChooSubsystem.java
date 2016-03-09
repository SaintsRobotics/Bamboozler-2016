package com.saintsrobotics.subsystem;

import com.saintsrobotics.Robot;
import com.saintsrobotics.Sensor;

import edu.wpi.first.wpilibj.Timer;

public class ChooChooSubsystem {

    private Timer timer = new Timer();
    private boolean firing = false;

    public void wind() {
        wind(1);
    }

    public void wind(double speed) {
        if (!Sensor.LimitSwitches.CHOOCHOO.get() || firing) {
            Robot.MOTORS.CHOOCHOO().set(speed);
        } else {
            Robot.MOTORS.CHOOCHOO().set(0);
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

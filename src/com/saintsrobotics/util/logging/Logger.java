package com.saintsrobotics.util.logging;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;

public class Logger {

    private Timer timer = new Timer();
    private long millis;

    public Logger(int millis) {
        this.millis = millis;
        timer.start();
    }

    public void m(Object o) {
        if (o == null) return;
        if (timer.hasPeriodPassed(millis/1000.0)) {
            DriverStation.reportError(o.toString(), false);
        }
    }
}

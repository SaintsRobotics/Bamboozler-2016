package com.saintsrobotics.util;

import edu.wpi.first.wpilibj.DriverStation;

public class ContinuousLog {

    private int cnt = 0;

    public void log(Object message) {
        if (cnt++ % 1 == 0) DriverStation.reportError(message.toString(), false);
    }
}

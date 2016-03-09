package com.saintsrobotics.util;

import edu.wpi.first.wpilibj.DriverStation;

public class ContinuousLog {

    private int cnt = 0;

    public void log(Object message) {
        if (cnt++ % 400 == 0) DriverStation.reportError(message.toString(), false);
    }
}

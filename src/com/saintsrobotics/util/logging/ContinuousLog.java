package com.saintsrobotics.util.logging;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import edu.wpi.first.wpilibj.DriverStation;

public class ContinuousLog {

    private List<ContinuousLogObject> logs = new LinkedList<>();

    // Loop through all the logging objects in the queue, and check if they need to be logged/removed
    public void runLog() {
        for (Iterator<ContinuousLogObject> iterator = logs.iterator(); iterator.hasNext(); ) {
            ContinuousLogObject msg = iterator.next();
            if (msg.isLogging()) DriverStation.reportError(msg.getMessage(), false);
            if (msg.remove()) iterator.remove();
        }
    }

    public void logContinuous(ContinuousLogObject obj) {
        logs.add(obj);
    }
}

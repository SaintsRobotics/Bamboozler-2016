package com.saintsrobotics.util.logging;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;

public class Logger {

    private Timer timer = new Timer();
    private long millis;

    public Logger(int millis) {
        this.millis = millis;
        start();
    }
    
    public void start() {
    	timer.stop();
    	timer.reset();
    	timer.start();
    }

    public void m(Object... os) {
        if (os == null || os.length == 0) return;
        if (timer.hasPeriodPassed(millis/1000.0)) {
        	StringBuilder sb = new StringBuilder(os[0].toString());
        	for (int i=1; i<os.length; i++) {
        		sb.append(", ").append(os[i]);
        	}
            DriverStation.reportError(sb.toString(), false);
        }
    }
}

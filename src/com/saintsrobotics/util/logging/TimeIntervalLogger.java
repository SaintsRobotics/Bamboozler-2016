package com.saintsrobotics.util.logging;

import com.saintsrobotics.Robot;

public class TimeIntervalLogger implements ContinuousLogObject {

    public boolean delete = false;
    public long intervalTime;
    private long hitTime = 0;
    private String value;

    public TimeIntervalLogger(double intervalTime) {
        this(intervalTime, 0);
    }

    public TimeIntervalLogger(double intervalTime, double firstDelay) {
        this.intervalTime = secToNano(intervalTime);
        hitTime = System.nanoTime() + secToNano(firstDelay);
        Robot.log.logContinuous(this);
    }

    public void setValue(Object toLog) {
        value = toLog.toString();
    }

    @Override
    public String getMessage() {
        return value;
    }

    @Override
    public boolean isLogging() {
        if (System.nanoTime() > hitTime) {
            hitTime = System.nanoTime() + intervalTime;
            return true;
        }
        return false;
    }

    @Override
    public boolean remove() {
        return delete;
    }


    private long secToNano(double sec) {
        return (long) (sec * 1000000000);
    }

}

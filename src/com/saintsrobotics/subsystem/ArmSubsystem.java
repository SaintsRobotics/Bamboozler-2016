package com.saintsrobotics.subsystem;

import com.saintsrobotics.Robot;
import com.saintsrobotics.Sensor;
import com.saintsrobotics.util.PID;
import com.saintsrobotics.util.logging.Logger;


public class ArmSubsystem {

    public PID elbowPid = new PID(1, 0, 0);
    public PID armPid = new PID(0.01, 0, 0);
    
    Logger log = new Logger(500);

    public void set(double armPos, double elbowPos) {                                                                                                                                                                                                                                                                                                                        
        elbowPos = Math.max(elbowPos, 0);
        armPos = Math.max(armPos, 0);

        double armVal = armPid.compute(Sensor.Potentiometer.ARM.get(), armPos);
        double elbowVal = elbowPid.compute(Sensor.Potentiometer.ELBOW.get(), elbowPos);
        Robot.MOTORS.ARM_AXLE().set(armVal);
        Robot.MOTORS.ARM_WINCH().set(elbowVal);
        
        log.m(elbowVal + " " + Sensor.Potentiometer.ELBOW.get() + " " + elbowPos);
    }

    public double calcDistance(double axle, double winch, double length){
        winch = Math.toRadians(Math.abs(axle-winch));
        axle = Math.toRadians(axle);
        return (Math.cos(winch) - Math.cos(axle)) * length;
    }
}

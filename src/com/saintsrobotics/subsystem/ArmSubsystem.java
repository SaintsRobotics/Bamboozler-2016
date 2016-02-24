package com.saintsrobotics.subsystem;

import com.saintsrobotics.Motors;
import com.saintsrobotics.Robot;
import com.saintsrobotics.Sensor;
import com.saintsrobotics.util.PID;


public class ArmSubsystem {

    public PID elbowPid = new PID(1f/270*64, 0, 0);
    public PID armPid = new PID(1f/270*8, 0, 0); //0.04
    
    int cnt;

public void set(double armPos, double elbowPos) {                                                                                                                                                                                                                                                                                                                        
        elbowPos = Math.max(elbowPos, 0);
        
        double armVal = armPid.compute(Sensor.Potentiometer.ARM.get(), armPos);
        double elbowVal = elbowPid.compute(Sensor.Potentiometer.ELBOW.get(), elbowPos);
        Motors.ARM_AXLE.set(armVal);
        Motors.ARM_WINCH.set(elbowVal);
        
        if (cnt++ % 400 == 0) {
            Robot.log(elbowVal + " " + Sensor.Potentiometer.ELBOW.get() + " " + elbowPos);
        }
    }
}

package com.saintsrobotics.subsystem;

import com.saintsrobotics.Motors;
import com.saintsrobotics.Robot;
import com.saintsrobotics.Sensor;
import com.saintsrobotics.util.PID;


public class ArmSubsystem {

    public PID elbowPid = new PID(1, 0, 0);
    public PID armPid = new PID(0.01, 0, 0); //0.04
    
    int cnt;

    public void set(double armPos, double elbowPos) {                                                                                                                                                                                                                                                                                                                        
        elbowPos = Math.max(elbowPos, 0);
        armPos = Math.max(armPos, 0);
        
        /*if(calcDistance(armPos, elbowPos, 29.0) > 15.0){
        	Motors.ARM_AXLE.set(0);
        	Motors.ARM_WINCH.set(0);
        	return;
        }*/
        double armVal = armPid.compute(Sensor.Potentiometer.ARM.get(), armPos);
        double elbowVal = elbowPid.compute(Sensor.Potentiometer.ELBOW.get(), elbowPos);
        Motors.ARM_AXLE.set(armVal);
        Motors.ARM_WINCH.set(elbowVal);
        
        if (Robot.debug && cnt++ % 400 == 0) {
            Robot.log(elbowVal + " " + Sensor.Potentiometer.ELBOW.get() + " " + elbowPos);
        }
    }
    public double calcDistance(double axle, double winch, double length){
    	winch = Math.toRadians(Math.abs(axle-winch));
    	axle = Math.toRadians(axle);
    	return (Math.cos(winch) - Math.cos(axle)) * length;
    }
}

package com.saintsrobotics;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;

public enum Motors {
    
    DRIVE_LEFT_1(new Victor(2), true),
    DRIVE_LEFT_2(new Victor(3), true),
    DRIVE_RIGHT_1(new Victor(0), false),
    DRIVE_RIGHT_2(new Victor(1), false),
    PICKUP(new Talon(5), false),
    ARM_AXLE(new Talon(7), true),
    ARM_WINCH(new Talon(6), false),
    CHOOCHOO(new Talon(4), true);

    private SpeedController motor;

    Motors(SpeedController motor, boolean inverted) {
        this.motor = motor;
        motor.setInverted(inverted);
    }

    public double get() {
        return motor.get();
    }

    public void set(double speed) {
        motor.set(speed);
    }
    public void stop(){
    	motor.set(-motor.get());
    }
    public SpeedController getRawMotor(){
    	return motor;
    }
}

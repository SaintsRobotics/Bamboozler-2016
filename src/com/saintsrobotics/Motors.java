package com.saintsrobotics;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;

public enum Motors {

    DRIVE_LEFT_1(new Talon(2), false),
    DRIVE_LEFT_2(new Talon(3), false),
    DRIVE_RIGHT_1(new Talon(0), true),
    DRIVE_RIGHT_2(new Talon(1), true),
    PICKUP(new Talon(6), false),
    ARM_AXLE(new Talon(7), false),
    ARM_WINCH(new Talon(5), true),
    CHOOCHOO(new Talon(4), false);

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

    public void stop() {
        motor.set(-motor.get());
    }
}

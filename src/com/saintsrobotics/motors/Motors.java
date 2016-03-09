package com.saintsrobotics.motors;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;

public abstract class Motors {

    public abstract Motor DRIVE_LEFT_1();
    public abstract Motor DRIVE_LEFT_2();
    public abstract Motor DRIVE_LEFT_3();
    public abstract Motor DRIVE_RIGHT_1();
    public abstract Motor DRIVE_RIGHT_2();
    public abstract Motor DRIVE_RIGHT_3();
    public abstract Motor PICKUP();
    public abstract Motor ARM_AXLE();
    public abstract Motor ARM_WINCH();
    public abstract Motor CHOOCHOO();

    Motor[] motors = new Motor[8];

    public Motor getMotor(int pin, boolean inverted) {
        if (motors[pin] == null) {
            motors[pin] = new Motor(new Victor(pin), inverted);
        }
        return motors[pin];
    }

    public class Motor {

        private SpeedController motor;

        public Motor(SpeedController motor, boolean inverted) {
            this.motor = motor;
            motor.setInverted(inverted);
        }

        public double get() {
            return motor.get();
        }

        public void set(double speed) {
            motor.set(speed);
        }
    }
}

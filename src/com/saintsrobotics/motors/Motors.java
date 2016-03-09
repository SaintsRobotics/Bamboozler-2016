package com.saintsrobotics.motors;

import edu.wpi.first.wpilibj.SpeedController;

public interface Motors {

    Motor DRIVE_LEFT_1();
    Motor DRIVE_LEFT_2();
    Motor DRIVE_LEFT_3();
    Motor DRIVE_RIGHT_1();
    Motor DRIVE_RIGHT_2();
    Motor DRIVE_RIGHT_3();
    Motor PICKUP();
    Motor ARM_AXLE();
    Motor ARM_WINCH();
    Motor CHOOCHOO();

    class Motor {

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

package com.saintsrobotics;

import edu.wpi.first.wpilibj.Joystick;

public class OI {

    public enum Axis {
        LX(0),
        LY(1),
        LT(2),
        RT(3),
        RX(4),
        RY(5);

        int rawIndex;

        Axis(int rawIndex) {
            this.rawIndex = rawIndex;
        }
    }

    public enum Button {
        A(1),
        B(2),
        X(3),
        Y(4),
        LB(5),
        RB(6),
        SELECT(7),
        START(8),
        L3(9),
        R3(10);

        int rawIndex;

        Button(int rawIndex) {
            this.rawIndex = rawIndex;
        }
    }

    private Joystick stick;

    public OI() {
        stick = new Joystick(0);
        opStick = new Joystick(1);
    }

    public boolean getButton(Button button) {
        return stick.getRawButton(button.rawIndex);
    }

    public double getAxis(Axis axis) {
        return stick.getRawAxis(axis.rawIndex);
    }
    
    private Joystick opStick;
    public boolean getOpButton(Button button) {
        return opStick.getRawButton(button.rawIndex);
    }

    public double getOpAxis(Axis axis) {
        return opStick.getRawAxis(axis.rawIndex);
    }

}

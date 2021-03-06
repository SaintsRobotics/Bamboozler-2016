package com.saintsrobotics;

import edu.wpi.first.wpilibj.Joystick;

public class OI {

    public enum Axis {
        LX(0),
        LY(1),
        LT(2),
        RT(3),
        RX(4),
        RY(5),
        ARM(0),
        ELBOW(1),
        CONTROL_BOARD_KNOB(2),
        LOGITECH_KNOB(2);

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

    private Joystick driveStick;
    private Joystick controlBoard;
    private Joystick operatorStick; // we'll likely get rid of this

    public OI() {
        driveStick = new Joystick(0);
        controlBoard = new Joystick(1);
        operatorStick = new Joystick(2);
    }

    public boolean getDrive(Button button) {
        return driveStick.getRawButton(button.rawIndex);
    }

    public double getDrive(Axis axis) {
        double val = driveStick.getRawAxis(axis.rawIndex);
        if (Math.abs(val) < 0.13) val = 0;
        return val;
    }

    public double getControlBoard(Axis axis) {
        // scale [-1, 1] to [0, 270]
        return ((controlBoard.getRawAxis(axis.rawIndex)+1)/2)*270;
    }

    public boolean getOperator(Button button) {
        return operatorStick.getRawButton(button.rawIndex);
    }

    public double getOperator(Axis axis) {
        return operatorStick.getRawAxis(axis.rawIndex);
    }

}

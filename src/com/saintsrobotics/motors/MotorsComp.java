package com.saintsrobotics.motors;

import edu.wpi.first.wpilibj.Talon;

public class MotorsComp extends Motors {

    public Motor DRIVE_LEFT_1()  { return getMotor(0, false); }
    public Motor DRIVE_LEFT_2()  { return getMotor(1, false); }
    public Motor DRIVE_LEFT_3()  { return getMotor(2, false); }
    public Motor DRIVE_RIGHT_1() { return getMotor(3, true ); }
    public Motor DRIVE_RIGHT_2() { return getMotor(4, false); }
    public Motor DRIVE_RIGHT_3() { return getMotor(5, false); }
    public Motor PICKUP()        { return getMotor(6, false); }
    public Motor ARM_AXLE()      { return getMotor(7, false); }
    public Motor ARM_WINCH()     { return getMotor(8, true ); }
    public Motor CHOOCHOO()      { return getMotor(9, false); }
}
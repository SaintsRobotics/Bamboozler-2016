package com.saintsrobotics.motors;

public class MotorsPractice extends Motors {

    public Motor DRIVE_LEFT_1()  { return getMotor(12, true ); }
    public Motor DRIVE_LEFT_2()  { return getMotor( 6, true ); }
    public Motor DRIVE_LEFT_3()  { return getMotor(14, true ); }
    public Motor DRIVE_RIGHT_1() { return getMotor( 1, false); }
    public Motor DRIVE_RIGHT_2() { return getMotor( 2, false); }
    public Motor DRIVE_RIGHT_3() { return getMotor(13, false); }
    public Motor PICKUP()        { return getMotor( 8, false); }
    public Motor ARM_AXLE()      { return getMotor( 5, false); }
    public Motor ARM_WINCH()     { return getMotor( 7, true ); }
    public Motor CHOOCHOO()      { return getMotor(11, false); }
}
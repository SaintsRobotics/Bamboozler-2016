package com.saintsrobotics.motors;

public class MotorsPractice extends Motors {

    public Motor DRIVE_LEFT_1()  { return getMotor( 5, false); }
    public Motor DRIVE_LEFT_2()  { return getMotor( 6, false); }
    public Motor DRIVE_LEFT_3()  { return getMotor( 7, false); }
    public Motor DRIVE_RIGHT_1() { return getMotor( 1, true ); }
    public Motor DRIVE_RIGHT_2() { return getMotor( 2, true ); }
    public Motor DRIVE_RIGHT_3() { return getMotor( 3, true ); }
    public Motor PICKUP()        { return getMotor( 8, false); }
    public Motor ARM_AXLE()      { return getMotor( 9, false); }
    public Motor ARM_WINCH()     { return getMotor(10, true ); }
    public Motor CHOOCHOO()      { return getMotor(11, false); }
}

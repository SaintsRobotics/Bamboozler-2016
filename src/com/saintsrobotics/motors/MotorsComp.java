package com.saintsrobotics.motors;

import edu.wpi.first.wpilibj.Talon;

public class MotorsComp implements Motors {

    public Motor DRIVE_LEFT_1()  { return new Motor(new Talon(12), true ); }
    public Motor DRIVE_LEFT_2()  { return new Motor(new Talon( 6), true ); }
    public Motor DRIVE_LEFT_3()  { return new Motor(new Talon(14), true ); }
    public Motor DRIVE_RIGHT_1() { return new Motor(new Talon( 1), false); }
    public Motor DRIVE_RIGHT_2() { return new Motor(new Talon( 2), false); }
    public Motor DRIVE_RIGHT_3() { return new Motor(new Talon(13), false); }
    public Motor PICKUP()        { return new Motor(new Talon( 8), false); }
    public Motor ARM_AXLE()      { return new Motor(new Talon( 5), false); }
    public Motor ARM_WINCH()     { return new Motor(new Talon( 7), true ); }
    public Motor CHOOCHOO()      { return new Motor(new Talon(11), false); }
}
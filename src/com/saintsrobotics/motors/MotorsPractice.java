package com.saintsrobotics.motors;

import edu.wpi.first.wpilibj.Talon;

public class MotorsPractice implements Motors {

    public Motor DRIVE_LEFT_1()  { return new Motor(new Talon( 5), false ); }
    public Motor DRIVE_LEFT_2()  { return new Motor(new Talon( 6), false ); }
    public Motor DRIVE_LEFT_3()  { return new Motor(new Talon( 7), false ); }
    public Motor DRIVE_RIGHT_1() { return new Motor(new Talon( 1), true); }
    public Motor DRIVE_RIGHT_2() { return new Motor(new Talon( 2), true); }
    public Motor DRIVE_RIGHT_3() { return new Motor(new Talon( 3), true); }
    public Motor PICKUP()        { return new Motor(new Talon( 8), false); }
    public Motor ARM_AXLE()      { return new Motor(new Talon( 9), false); }
    public Motor ARM_WINCH()     { return new Motor(new Talon( 10), true ); }
    public Motor CHOOCHOO()      { return new Motor(new Talon(11), false); }
}

//
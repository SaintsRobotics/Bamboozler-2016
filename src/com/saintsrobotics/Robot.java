package com.saintsrobotics;

import com.saintsrobotics.OI.Axis;
import com.saintsrobotics.motors.Motors;
import com.saintsrobotics.motors.MotorsPractice;
import com.saintsrobotics.subsystem.ArmSubsystem;
import com.saintsrobotics.subsystem.CameraSubsystem;
import com.saintsrobotics.subsystem.ChooChooSubsystem;
import com.saintsrobotics.subsystem.DriveSubsystem;
import com.saintsrobotics.subsystem.PickupSubsystem;

import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Timer;

public class Robot extends SampleRobot {

    public static final Motors MOTORS = new MotorsPractice();

    OI oi = new OI();
    DriveSubsystem drive = new DriveSubsystem();
    PickupSubsystem pickup = new PickupSubsystem();
    ArmSubsystem arm = new ArmSubsystem();
    ChooChooSubsystem choochoo = new ChooChooSubsystem();
    CameraSubsystem camera = new CameraSubsystem();

    public void operatorControl() {
        while (isOperatorControl() && isEnabled()) {
            drive.driveArcade(oi.getDrive(OI.Axis.LY), oi.getDrive(OI.Axis.RX));
            choochoo.wind();

            // values are in degrees
            arm.set(oi.getControlBoard(Axis.ARM), oi.getControlBoard(Axis.ELBOW));

            // input change 0 -> 180 to 0 -> 1
            pickup.set(1-oi.getControlBoard(Axis.CONTROL_BOARD_KNOB)/182);
            if (oi.getOperator(OI.Button.A)) choochoo.fire();
        }
    }

    public void test(){
    	while(isTest() && isEnabled()){
    		arm.set(oi.getControlBoard(Axis.ARM), oi.getControlBoard(Axis.ELBOW));
    		MOTORS.ARM_AXLE().set(1);
    	}
    }

    public void autonomous() {
    	double timeToStop = 7;
    	Timer timer = new Timer();
    	while (isAutonomous() && !isDisabled()) {
    			pickup.set(1);
    			if (!timer.hasPeriodPassed(timeToStop)) {
    				drive.driveTank(-0.75, -0.75);
    			}
    			else {
    				drive.driveTank(0, 0);
    			}
    	}
    }

    public void robotInit() {
        camera.enable();
    }

    public void disabled() {
    	camera.end();
    }
}
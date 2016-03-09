package com.saintsrobotics;

import com.saintsrobotics.motors.Motors;
import com.saintsrobotics.motors.MotorsPractice;
import com.saintsrobotics.subsystem.ArmSubsystem;
import com.saintsrobotics.subsystem.CameraSubsystem;
import com.saintsrobotics.subsystem.ChooChooSubsystem;
import com.saintsrobotics.subsystem.DriveSubsystem;
import com.saintsrobotics.subsystem.PickupSubsystem;
import com.saintsrobotics.util.ContinuousLog;
import com.saintsrobotics.OI.Button;
import com.saintsrobotics.OI.Axis;

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
    
    ContinuousLog log = new ContinuousLog();

    public void operatorControl() {
        while (isOperatorControl() && isEnabled()) {
            drive.driveArcade(oi.getDrive(OI.Axis.LY), oi.getDrive(OI.Axis.RX));
//            choochoo.wind();

            // values are in degrees
            arm.set(oi.getControlBoard(Axis.ARM), oi.getControlBoard(Axis.ELBOW));

            // input change 0 -> 180 to 0 -> 1
            pickup.set(1-oi.getControlBoard(Axis.CONTROL_BOARD_KNOB)/182);
            if (oi.getOperator(OI.Button.A)) choochoo.fire();
        }
    }

    public void test(){
    	int cnt = 0;
    	int val = 4000;
    	while(isTest() && isEnabled()) {
    		cnt++;
    		if (cnt/val % 6 != 0) MOTORS.DRIVE_LEFT_1().set(0);
    		if (cnt/val % 6 != 1) MOTORS.DRIVE_LEFT_2().set(0);
    		if (cnt/val % 6 != 2) MOTORS.DRIVE_LEFT_3().set(0);
    		if (cnt/val % 6 != 3) MOTORS.DRIVE_RIGHT_1().set(0);
    		if (cnt/val % 6 != 4) MOTORS.DRIVE_RIGHT_2().set(0);
    		if (cnt/val % 6 != 5) MOTORS.DRIVE_RIGHT_3().set(0);

    		switch (cnt/val % 6) {
                case 0: MOTORS.DRIVE_LEFT_1().set(1); break;
                case 1: MOTORS.DRIVE_LEFT_2().set(1); break;
                case 2: MOTORS.DRIVE_LEFT_3().set(1); break;
                case 3: MOTORS.DRIVE_RIGHT_1().set(1); break;
                case 4: MOTORS.DRIVE_RIGHT_2().set(1); break;
                case 5: MOTORS.DRIVE_RIGHT_3().set(1); break;
    		}
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
//        camera.enable();
    }

    public void disabled() {
//    	camera.end();
    }
}
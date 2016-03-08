package com.saintsrobotics;

import com.saintsrobotics.OI.Axis;
import com.saintsrobotics.OI.Button;
import com.saintsrobotics.subsystem.ArmSubsystem;
import com.saintsrobotics.subsystem.CameraSubsystem;
import com.saintsrobotics.subsystem.ChooChooSubsystem;
import com.saintsrobotics.subsystem.DriveSubsystem;
import com.saintsrobotics.subsystem.PickupSubsystem;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class Robot extends SampleRobot {
	//Set true if you want to debug the robot. Or not. Or whatever. This'll just make the debug calls actually run. 
	//I mean, if you're into that I'm not going to judge, but I'm just going to assume it's only true if you want to debug, kay?
	public static boolean debug = false;
	//OI. Getting user input
    OI oi = new OI();
    //Drive. Controlls drive things
    DriveSubsystem drive = new DriveSubsystem();
    //pickup. Ditto
    PickupSubsystem pickup = new PickupSubsystem();
    //there may or may not be a pattern here
    ArmSubsystem arm = new ArmSubsystem();
    //Nope definitely no pattern
    ChooChooSubsystem choochoo = new ChooChooSubsystem();
    CameraSubsystem camera = new CameraSubsystem();
    
    //Counter
    int cnt = 0;

    public void operatorControl() {
    	if (isOperatorControl() && isEnabled()){
    		camera.enable();
    	}
        while (isOperatorControl() && isEnabled()) {
        	//Set the drive speed to base of 0.5, and ramp it up with the right trigger
        	//Arcade drive with these values.
            drive.driveArcade(oi.getDrive(OI.Axis.LY), oi.getDrive(OI.Axis.RX));
            //wind the bloody choochoo. Actually, at the torque that thing has, it could very well draw blood.
            choochoo.wind();
            // Set the arm joints to the control board values. Values are in Degrees
            arm.set(oi.getControlBoard(Axis.ARM), oi.getControlBoard(Axis.ELBOW));
            
                        // input change 0 -> 180 to 0 -> 1
            pickup.set(1-oi.getControlBoard(Axis.CONTROL_BOARD_KNOB)/182);
            if (oi.getOperator(OI.Button.A)) choochoo.fire();
        }
    }
    public void test(){
    	while(isTest() && isEnabled()){
            drive.driveArcade(oi.getDrive(OI.Axis.LY), oi.getDrive(OI.Axis.RX));
    	}
    }
    
    public void autonomous() {
    	/*gggg
    	double timeToStopgggggg = 7;
    	Timer timer = negw Timer();
    	while (isAutonomous() && !isDisabled()) {
    			pickup.set(1);
    			if (!timer.hasPeriodPassed(timeToStop)) {
    				drive.driveTank(-0.75, -0.75);
    			}
    			else {
    				drive.driveTank(0, 0);
    			}
    	}
    	*/
    }
    
    public void disabled() {
    	camera.end();
    }

    public static void log(String message) {
        DriverStation.reportError(message, false);
    }
}
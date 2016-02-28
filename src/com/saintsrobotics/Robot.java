package com.saintsrobotics;

import com.saintsrobotics.OI.Axis;
import com.saintsrobotics.OI.Button;
import com.saintsrobotics.subsystem.ArmSubsystem;
import com.saintsrobotics.subsystem.ChooChooSubsystem;
import com.saintsrobotics.subsystem.DriveSubsystem;
import com.saintsrobotics.subsystem.PickupSubsystem;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Timer;

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
    
    //Counter
    int cnt = 0;

    public void operatorControl() {
        while (isOperatorControl() && isEnabled()) {
        	//Set the drive speed to base of 0.5, and ramp it up with the right trigger
        	drive.multiplier = oi.getDrive(Axis.RT) * 0.5 + 0.5;
        	//Arcade drive with these values.
            drive.driveArcade(-oi.getDrive(OI.Axis.LY), oi.getDrive(OI.Axis.RX));       
            //wind the bloody choochoo. Actually, at the torque that thing has, it could very well draw blood.
            choochoo.wind();
            //TODO: Set the arm joints to the control board values. These should be hardcoded in the OI, but too lazy to change rn
            arm.set(oi.getControlBoard(Axis.ARM) - 10.546875, oi.getControlBoard(Axis.ELBOW) - 4.21875);

            
            
                        // input change 0 -> 180 to 0 -> 1
            pickup.set(oi.getControlBoard(Axis.CONTROL_BOARD_KNOB)/182);

            if (oi.getOperator(OI.Button.A)) choochoo.fire();
            // ability to manually zero arms would be nice
        }
    }
    
    
    public void autonomous() {
    	int forward = 5;
    	int backward = -4;
    	Timer timer = new Timer();
    	while (isAutonomous() && !isDisabled()) {
    		pickup.set(1);
    		if (!timer.hasPeriodPassed(forward+backward)) {
    			drive.driveTank(-0.5, -0.5);
    		}
    		else {
    			drive.driveTank(0, 0);
    		}
    	}
    	
    }
    
    public void disabled() {
    }

    public static void log(String message) {
        DriverStation.reportError(message, false);
    }
}

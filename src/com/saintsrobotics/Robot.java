package com.saintsrobotics;

import com.saintsrobotics.OI.Axis;
import com.saintsrobotics.subsystem.ArmSubsystem;
import com.saintsrobotics.subsystem.ChooChooSubsystem;
import com.saintsrobotics.subsystem.DriveSubsystem;
import com.saintsrobotics.subsystem.PickupSubsystem;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SampleRobot;

public class Robot extends SampleRobot {

    OI oi = new OI();
    DriveSubsystem drive = new DriveSubsystem();
    PickupSubsystem pickup = new PickupSubsystem();
    ArmSubsystem arm = new ArmSubsystem();
    ChooChooSubsystem choochoo = new ChooChooSubsystem();
    
    int cnt = 0;

    public void operatorControl() {
        pickup.zero();
        while (isOperatorControl() && isEnabled()) {
            drive.driveArcade(oi.getDrive(OI.Axis.LY), oi.getDrive(OI.Axis.RX));
//            choochoo.wind();
//            arm.set(oi.getControlBoard(Axis.ARM) - 10.546875, oi.getControlBoard(Axis.ELBOW) - 4.21875);

            // 
            
//            if (cnt++ % 100 == 0) log("" + oi.getControlBoard(Axis.CONTROL_BOARD_KNOB));
            // input change 0 -> 180 to 0 -> -1
//             pickup.set(-oi.getControlBoard(Axis.CONTROL_BOARD_KNOB)/182);

//            if (oi.getOperator(OI.Button.A)) choochoo.brakakaka();
            // ability to manually zero arms would be nice
        }
    }

    public static void log(String message) {
        DriverStation.reportError(message, false);
    }
}

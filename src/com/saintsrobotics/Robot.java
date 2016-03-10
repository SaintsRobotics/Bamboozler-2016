package com.saintsrobotics;

import com.saintsrobotics.motors.Motors;
import com.saintsrobotics.motors.MotorsPractice;
import com.saintsrobotics.subsystem.ArmSubsystem;
import com.saintsrobotics.subsystem.ChooChooSubsystem;
import com.saintsrobotics.subsystem.DriveSubsystem;
import com.saintsrobotics.subsystem.PickupSubsystem;
import com.saintsrobotics.util.logging.ContinuousLog;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class Robot extends SampleRobot {

    public static final Motors MOTORS = new MotorsPractice();

    OI oi = new OI();
    DriveSubsystem drive = new DriveSubsystem();
    PickupSubsystem pickup = new PickupSubsystem();
    ArmSubsystem arm = new ArmSubsystem();
    ChooChooSubsystem choochoo = new ChooChooSubsystem();
    
    public static ContinuousLog log = new ContinuousLog();

    public void operatorControl() {
        CameraServer.getInstance().startAutomaticCapture();
        while (isOperatorControl() && isEnabled()) {
            drive.driveArcade(oi.getDrive(OI.Axis.LY), oi.getDrive(OI.Axis.RX));
            choochoo.wind();

            // values are in degrees
            arm.set(oi.getControlBoard(OI.Axis.ARM), oi.getControlBoard(OI.Axis.ELBOW));

            // input change 0 -> 180 to 0 -> 1
            pickup.set(1-oi.getControlBoard(OI.Axis.CONTROL_BOARD_KNOB)/182);
//            if (oi.getOperator(OI.Button.A)) choochoo.fire();
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

    public void test() {
        LiveWindow.setEnabled(true);
        LiveWindow.run();
        LiveWindow.addSensor("Pickup", "Encoder", Sensor.Encoders.PICKUP.getRaw());
        for (int cnt = 0; isTest() && isEnabled(); cnt++) {
            testDriveMotors(cnt, 1000);
            testPickupMotor(cnt);
            // Afterwards check the encoder on the LiveWindow and manually calculate the end position.
//            testPickup();
        }
    }

    /**
     * Moves the pickup back until it hits the limit switch then stops.
     */
    private void testPickup() {
        if (!pickup.isSet()) {
            pickup.set(0);
        }
    }

    /**
     * Runs the pickup motor in the forward direction for a short duration.
     */
    private void testPickupMotor(int cnt) {
        if (cnt < 500) {
            MOTORS.PICKUP().set(0.2);
        }
    }

    /**
     * Run each drive motor in sequence at 0.2 power forward.
     */
    private void testDriveMotors(int cnt, int timeEach) {
        if (cnt/timeEach % 6 != 0) MOTORS.DRIVE_LEFT_1().set(0);
        if (cnt/timeEach % 6 != 1) MOTORS.DRIVE_LEFT_2().set(0);
        if (cnt/timeEach % 6 != 2) MOTORS.DRIVE_LEFT_3().set(0);
        if (cnt/timeEach % 6 != 3) MOTORS.DRIVE_RIGHT_1().set(0);
        if (cnt/timeEach % 6 != 4) MOTORS.DRIVE_RIGHT_2().set(0);
        if (cnt/timeEach % 6 != 5) MOTORS.DRIVE_RIGHT_3().set(0);

        switch (cnt/timeEach % 6) {
            case 0: MOTORS.DRIVE_LEFT_1().set(0.2); break;
            case 1: MOTORS.DRIVE_LEFT_2().set(0.2); break;
            case 2: MOTORS.DRIVE_LEFT_3().set(0.2); break;
            case 3: MOTORS.DRIVE_RIGHT_1().set(0.2); break;
            case 4: MOTORS.DRIVE_RIGHT_2().set(0.2); break;
            case 5: MOTORS.DRIVE_RIGHT_3().set(0.2); break;
        }
    }
}
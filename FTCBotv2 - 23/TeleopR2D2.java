
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Blinker;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;



@TeleOp

public class CenterStagev3 extends LinearOpMode {
    private Blinker control_Hub;
    private Blinker expansion_Hub_2;
    private DcMotor armMotor;
    private DcMotor backLeftDrive;
    private DcMotor backRightDrive;
    private Servo claw;
    private Servo droneLauncher;
    private DcMotor frontLeftDrive;
    private DcMotor frontRightDrive;
    private IMU imu;
    private Servo suspensionOne;
    private Servo suspensionTwo;
    


    @Override
    public void runOpMode() {
        control_Hub = hardwareMap.get(Blinker.class, "Control Hub");
        expansion_Hub_2 = hardwareMap.get(Blinker.class, "Expansion Hub 2");
        imu = hardwareMap.get(IMU.class, "imu");
        backLeftDrive = hardwareMap.get(DcMotor.class, "backLeftDrive");
        backRightDrive = hardwareMap.get(DcMotor.class, "backRightDrive");
        frontLeftDrive = hardwareMap.get(DcMotor.class, "frontLeftDrive");
        frontRightDrive = hardwareMap.get(DcMotor.class, "frontRightDrive");
        suspensionOne = hardwareMap.get(Servo.class, "suspensionOne");
        suspensionTwo = hardwareMap.get(Servo.class, "suspensionTwo");
        armMotor = hardwareMap.get(DcMotor.class, "armMotor");
        claw = hardwareMap.get(Servo.class, "claw");
        droneLauncher = hardwareMap.get(Servo.class, "droneLauncher");
        
        int armUpPosition = 537.6; 
        int armDownPosition = 0;
        armMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        armMotor.setTargetPosition(armDownPosition);
        armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        
        //armMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER); 
        
        // Retrieve the IMU from the hardware map
        IMU imu = hardwareMap.get(IMU.class, "imu");
        // Adjust the orientation parameters to match your robot
        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.FORWARD));
        // Without this, the REV Hub's orientation is assumed to be logo up / USB forward
        imu.initialize(parameters);

        telemetry.addData("Status", "Initialized");
        telemetry.update();
        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            
            // Get the current position of the armMotor
            double position = armMotor.getCurrentPosition();

            // Get the target position of the armMotor
            double desiredPosition = armMotor.getTargetPosition();

 
            double y = -gamepad1.left_stick_y; // Remember, Y stick value is reversed
            double x = gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing
            double rx = gamepad1.right_stick_x;
            // Denominator is the largest motor power (absolute value) or 1
            // This ensures all the powers maintain the same ratio,
            // but only if at least one is out of the range [-1, 1]

            if (gamepad1.options) {
                imu.resetYaw();
            }

            double botHeading = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);

            // Rotate the movement direction counter to the bot's rotation
            double rotX = x * Math.cos(-botHeading) - y * Math.sin(-botHeading);
            double rotY = x * Math.sin(-botHeading) + y * Math.cos(-botHeading);

            rotX = rotX * 1.1;  // Counteract imperfect strafing

            double denominator = Math.max(Math.abs(rotY) + Math.abs(rotX) + Math.abs(rx), 1);
            double frontLeftPower = (rotY + rotX + rx) / denominator;
            double backLeftPower = (rotY - rotX + rx) / denominator;
            double frontRightPower = (rotY - rotX - rx) / denominator;
            double backRightPower = (rotY + rotX - rx) / denominator;

            backLeftDrive.setPower(backLeftPower);
            backRightDrive.setPower(backRightPower);
            frontLeftDrive.setPower(frontLeftPower);
            frontRightDrive.setPower(frontRightPower);

            if (gamepad1.right_bumper) {
               armMotor.setTargetPosition(armUpPosition);
               armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
               armMotor.setPower(0.5);
            }
            else {
                armMotor.setPower(0);
            }
            // If the B button is pressed, lower the arm
            if (gamepad1.left_bumper) {
               armMotor.setTargetPosition(armDownPosition);
               armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
               armMotor.setPower(-0.5); 
            }
            else {
                armMotor.setPower(0);
            }

            if (gamepad1.a) {
                claw.setpower(.1);
            }
            else {
                claw.setPower(0);
            }

            if (gamepad1.x) {
                claw.setpower(.-1); 
            }
            else {
                claw.setPower(0);
            }
            
            if (gamepad1.right_trigger) {
                suspensionOne.setPower(.5);
                suspensionTwo.setPower(.5);
            }
            else {
                suspensionOne.setPower(0);
                suspensionTwo.setPower(0);
            }

            if (gamepad1.left_trigger) {
                suspensionOne.setPower(-.5);
                suspensionTwo.setPower(-.5);
            }
            else {
                suspensionOne.setPower(0);
                suspensionTwo.setPower(0);
            }

            if (dpad_up) {
                droneLauncher.setPower(-.2); 
            }
            else {
                droneLauncher.setPower(0);
            }

            // Show the position of the armMotor on telemetry
            telemetry.addData("Encoder Position", position);
            // Show the target position of the armMotor on telemetry
            telemetry.addData("Desired Position", desiredPosition);
            telemetry.addData("Status", "Running");
            telemetry.update();
            
        }
    }
}
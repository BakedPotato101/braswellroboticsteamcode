// Import necessary libraries
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.Range;

// Define your teleop class
@TeleOp(name = "TeleopR2D2")
public class TeleopR2D2 extends OpMode {

    // Declare your hardware map variables
    private DcMotor frontLeftMotor;
    private DcMotor frontRightMotor;
    private DcMotor backLeftMotor;
    private DcMotor backRightMotor;
    private DcMotor armMotor;
    private DcMotor clawServo; 

    // Define variables for drive control
    private double driveSpeed = 1.0;  // Adjust the drive speed as needed
    private double turnSpeed = 0.8;   // Adjust the turn speed as needed

    // Define variables for arm control
    private double armPower = 0.5;    // Adjust the arm power as needed

    // Define variables for arm control
    private double clawOpenPosition  = 0.0; // Adjust the servo positions as needed
    private double clawClosedPosition = 1.0; 
    private double clawPosition = clawOpenPosition; 

    // Initialize your hardware map
    @Override
    public void init() {
        // Initialize your hardware map variables using the names configured on the robot controller
        frontLeftMotor = hardwareMap.dcMotor.get("front_left_motor");
        frontRightMotor = hardwareMap.dcMotor.get("front_right_motor");
        backLeftMotor = hardwareMap.dcMotor.get("back_left_motor");
        backRightMotor = hardwareMap.dcMotor.get("back_right_motor");
        armMotor = hardwareMap.dcMotor.get("arm_motor");
        clawServo = hardwareMap.servo.get("claw_servo"); 

        // Set the motor directions
        frontLeftMotor.setDirection(DcMotor.Direction.FORWARD);
        frontRightMotor.setDirection(DcMotor.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotor.Direction.FORWARD);
        backRightMotor.setDirection(DcMotor.Direction.REVERSE);
        armMotor.setDirection(DcMotor.Direction.FORWARD);

        // Set the motor zero power behavior
        frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        armMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        telemetry.addData("Robot initialized");
    }

    // Run your teleop program
    @Override
    public void loop() {
        // Drive control
        double drive = -gamepad1.left_stick_y * driveSpeed;
        double strafe = -gamepad1.left_stick_x * driveSpeed;
        double turn = gamepad1.right_stick_x * turnSpeed;

        double frontLeftPower = drive + strafe + turn;
        double frontRightPower = drive - strafe - turn;
        double backLeftPower = drive - strafe + turn;
        double backRightPower = drive + strafe - turn;

        // Normalize the values so none exceed +/- 1.0
        double maxPower = Math.max(Math.abs(frontLeftPower), Math.abs(frontRightPower));
        maxPower = Math.max(maxPower, Math.abs(backLeftPower));
        maxPower = Math.max(maxPower, Math.abs(backRightPower));

        if (maxPower > 1.0) {
            frontLeftPower /= maxPower;
            frontRightPower /= maxPower;
            backLeftPower /= maxPower;
            backRightPower /= maxPower;
        }

        // Set the motor powers
        frontLeftMotor.setPower(frontLeftPower);
        frontRightMotor.setPower(frontRightPower);
        backLeftMotor.setPower(backLeftPower);
        backRightMotor.setPower(backRightPower);

        // Arm control
        double armControl = -gamepad2.left_stick_y * armPower;
        armMotor.setPower(armControl);

        // Claw control
        if (gamepad2.a) {
            clawPosition = clawOpenPosition; // Open the claw
        } else if (gamepad2.b) {
            clawPosition = clawClosedPosition; // close the claw
        }

        clawServo.setPosition(clawPosition); 

        // Other teleop code (if any)

        // Telemetry (optional)
        telemetry.addData("Drive Power", drive);
        telemetry.addData("Strafe Power", strafe);
        telemetry.addData("Turn Power", turn);
        telemetry.addData("Arm Power", armControl);
        telemetry.addData("Claw Position", clawPositon);
        telemetry.update();
    }
}
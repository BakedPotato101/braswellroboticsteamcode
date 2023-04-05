package temp;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * This is NOT an opmode.
 *
 * This class can be used to define all the specific hardware for a single robot.
 * In this case that robot is a Pushbot.
 * See PushbotTeleopTank_Iterative and others classes starting with "Pushbot" for usage examples.
 *
 * This hardware class assumes the following device names have been configured on the robot:
 * Note:  All names are lower case and some have single spaces between words.
 *
 * Motor channel:  Left  drive motor:        "left_drive"
 * Motor channel:  Right drive motor:        "right_drive"
 * Motor channel:  Manipulator drive motor:  "left_arm"
 * Servo channel:  Servo to open left claw:  "left_hand"
 * Servo channel:  Servo to open right claw: "right_hand"
 */
public class ROBOTMOD_T
{
    /* Public OpMode members. */
    public DcMotor  FrontleftDrive   = null;
    public DcMotor  FrontrightDrive  = null;
    public DcMotor  BackleftDrive  = null;
    public DcMotor  BackrightDrive  = null;
   
    /* local OpMode members. */
    HardwareMap hwMap           =  null;
    private ElapsedTime period  = new ElapsedTime();

    /* Constructor */
    public ROBOTMOD_T(){

    }

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;

        // Define and Initialize Motors
        FrontleftDrive  = hwMap.get(DcMotor.class, "FrontleftMotor");
        FrontrightDrive = hwMap.get(DcMotor.class, "FrontrightMotor");
        BackleftDrive  = hwMap.get(DcMotor.class, "BackleftMotor");
        BackrightDrive = hwMap.get(DcMotor.class, "BackrightMotor");
        
        FrontleftDrive.setDirection(DcMotor.Direction.FORWARD); // Set to REVERSE if using AndyMark motors
        FrontrightDrive.setDirection(DcMotor.Direction.REVERSE);// Set to FORWARD if using AndyMark motors
        BackleftDrive.setDirection(DcMotor.Direction.FORWARD); // Set to REVERSE if using AndyMark motors
        BackrightDrive.setDirection(DcMotor.Direction.REVERSE);// Set to FORWARD if using AndyMark motors

        // Set all motors to zero power
        FrontleftDrive.setPower(0);
        FrontrightDrive.setPower(0);
        BackleftDrive.setPower(0);
        BackrightDrive.setPower(0);

        // Set all motors to run without encoders.
        // May want to use RUN_USING_ENCODERS if encoders are installed.
        FrontleftDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        FrontrightDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BackleftDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BackrightDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

    }
 }

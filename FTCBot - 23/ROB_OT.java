package temp;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

public class ROB_OT {
    public DcMotor  FrontleftDrive   = null;
    public DcMotor  FrontrightDrive  = null;
    public DcMotor  BackleftDrive  = null;
    public DcMotor  BackrightDrive  = null;

    HardwareMap hwMap = null;
    private ElapsedTime period = new ElapsedTime();

    public ROB_OT() {}

    public void init(HardwareMap ahwMap) {
        hwMap = ahwMap;

        // Define and Initialize Motors
        FrontleftDrive = hwMap.get(DcMotor.class, "FrontleftMotor");
        FrontrightDrive = hwMap.get(DcMotor.class, "FrontrightMotor");
        BackleftDrive = hwMap.get(DcMotor.class, "BackleftMotor");
        BackrightDrive = hwMap.get(DcMotor.class, "BackrightMotor");

        FrontleftDrive.setDirection(DcMotor.Direction.FORWARD); // Set to REVERSE if using AndyMark motors
        FrontrightDrive.setDirection(DcMotor.Direction.REVERSE);// Set to FORWARD if using AndyMark motors
        BackleftDrive.setDirection(DcMotor.Direction.FORWARD); // Set to REVERSE if using AndyMark motors
        BackrightDrive.setDirection(DcMotor.Direction.REVERSE);// Set to FORWARD if using AndyMark motors

        // Set all motors to zero power
        this.stopDriving();

        // Set all motors to run without encoders.
        // May want to use RUN_USING_ENCODERS if encoders are installed.
        FrontleftDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        FrontrightDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BackleftDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BackrightDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void stopDriving() {
        FrontleftDrive.setPower(0);
        FrontrightDrive.setPower(0);
        BackleftDrive.setPower(0);
        BackrightDrive.setPower(0);
    }
} 


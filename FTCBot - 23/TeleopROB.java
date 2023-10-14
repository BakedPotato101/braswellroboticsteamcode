package temp;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

/**
 * This OpMode uses the common Pushbot hardware class to define the devices on the robot.
 * All device access is managed through the HardwarePushbot class.
 * The code is structured as a LinearOpMode
 *
 * This particular OpMode executes a POV Game style Teleop for a PushBot
 * In this mode the left stick moves the robot FWD and back, the Right stick turns left and right.
 * It raises and lowers the claw using the Gampad Y and A buttons respectively.
 * It also opens and closes the claws slowly using the left and right Bumper buttons.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@TeleOp(name="Mecanum: Teleop POV", group="ROB_OT")

public class TeleopROB extends LinearOpMode {

    /* Declare OpMode members. */
    ROB_OT robot           = new ROB_OT();   // Use a Pushbot's hardware

    @Override
    public void runOpMode() {
        double x1 = 0; //left/right
        double y1 = 0; //right/back
        
        double fortyFiveInRads = -Math.PI/4;
        double cosine45 = Math.cos(fortyFiveInRads);
        double sine45 = Math.sin(fortyFiveInRads);
        
        double x2 = 0; 
        double y2 = 0; 

        
      

        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Say", "Hello Driver");    //
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            double spin = gamepad1.right_stick_x;//for controlling the spin 
           // double strafe = gampad1.left_stick_x; 
           
           //
             if (x1 < 0) {
                robot.FrontleftDrive.setPower(-1);
                robot.FrontrightDrive.setPower(1);
                robot.BackleftDrive.setPower(1);
                robot.BackrightDrive.setPower(-1);
            
            }
            else if (x1 > 0) {
                robot.FrontleftDrive.setPower(1);
                robot.FrontrightDrive.setPower(-1);
                robot.BackleftDrive.setPower(-1);
                robot.BackrightDrive.setPower(1);
            }
            
            if (Math.abs(spin) > 0.1) {
                //is someone is moving the right joystick, spin
                robot.FrontrightDrive.setPower(-spin);
                robot.BackrightDrive.setPower(-spin);
                
                robot.FrontleftDrive.setPower(spin);
                robot.BackleftDrive.setPower(spin);
            }
            else { 
                /* if no one is pressing the right joystick, do the normal
                    driving code
                */ 
                y1 = -gamepad1.left_stick_y;
                x1  =  gamepad1.left_stick_x;
                
                //need to rotate 45 degrees 
                y2 = y1*cosine45 + x1*sine45; 
                x2 = x1*cosine45 - y1*sine45; 
                
                //strafe code 
               
               /* // Normalize the values so neither exceed +/- 1.0 - dont know if we need
                max = Math.max(Math.abs(left), Math.abs(right));
                if (max > 1.0)
                {
                    left /= max;
                    right /= max;
                }
    */
                // Output the safe vales to the motor drives.
                robot.FrontleftDrive.setPower(y1);
                robot.FrontrightDrive.setPower(y1);
                robot.BackleftDrive.setPower(y1);
                robot.BackrightDrive.setPower(y1);
            }
          

            //Send telemetry message to signify robot running; 
            telemetry.addData("y1", "%.2f", y1);
            telemetry.addData("x1",  "%.2f", x1);
            telemetry.addData("y2", "%.2f", y2);
            telemetry.addData("x2",  "%.2f", x2);
            telemetry.update();

            // Pace this loop so jaw action is reasonable speed.
            sleep(50);
        }
    }
}

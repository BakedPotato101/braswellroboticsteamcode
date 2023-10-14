package temp;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;




@Autonomous(name="Auto Drive By Time", group="ROBOTMOD_T")


public class Autobots extends LinearOpMode {


    /* Declare OpMode members. */
    ROBOTMOD_T         robot   = new ROBOTMOD_T();   // Use a Pushbot's hardware
    private ElapsedTime     runtime = new ElapsedTime();


    @Override
    public void runOpMode() {


        robot.init(hardwareMap);
        //range == robot.distanceSensor.getDistance(DistanceUnit.INCH);


        telemetry.addData("Status", "Ready to run");    
        telemetry.update();


        // Wait for the game to start (driver presses PLAY)
        waitForStart();


        // Step through each leg of the path, ensuring that the Auto mode has not been stopped along the way


        // Step 1:  Drive forward for 3 seconds
        //robot.FrontleftDrive.setPower(1);
        //robot.FrontrightDrive.setPower(1);
        //robot.BackleftDrive.setPower(1);
        //robot.BackrightDrive.setPower(1);
        //robot.forward();
        runtime.reset();
        //while (opModeIsActive() && (runtime.seconds() < 1.0)) {
        //    telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
        //    telemetry.update();
       // }


        // Step 2:  Stop
        robot.stopDriving();
        
        
            telemetry.addData("Path", "Complete");
            telemetry.update();
            sleep(1000);

            /*If the robot's distance senser reads a distance of less than 10 inches
            then it will turn left until it a distance of more than 10 inches 
            which it will then move forward
            */
            if (robot.distanceSensor.getDistance(DistanceUnit.INCH) < 10.0) {
                
                //robot turns left 
                robot.FrontleftDrive.setPower(spin);
                robot.BackleftDrive.setPower(spin);
               
            
            }
            else (robot.distanceSensor.getDistance(DistanceUnit.INCH) > 10.0) {
                robot.forward();
            }

    }

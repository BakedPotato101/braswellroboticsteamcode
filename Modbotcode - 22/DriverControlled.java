//This file here is going to be our TeleOp that tells motors what to do
//We do this by first telling the robot what hardware to refer to... Our Hardware Map!
//Then we define our buttons or sticks that we will be using to control our robot
//Finally, we tell the phone what those sticks and buttons will do to our robot
//------------------------------------------------------------------------------------------------------------------------------------------ 
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.robot.Robot;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

//------------------------------------------------------------------------------------------------------------------------------------------ 

@TeleOp(name="DriverControlled", group="TeleOp")

public class DriverControlled extends OpMode{

//------------------------------------------------------------------------------------------------------------------------------------------ 
                                            //this tells the robot what hardware it has, we don't have to type all of it out because we simply have
                                            //to refer to the hardware map we made
    //DEFINE ROBOT
    RobotHardware robot       = new RobotHardware();

//------------------------------------------------------------------------------------------------------------------------------------------   
                                            //This is the code that will run once the robot has initialized
   //Run once on init()
    @Override
    public void init()
 {            
    robot.init(hardwareMap); //this tells the robot to use the hardware map
    telemetry.addData("STATUS", "Initialized"); //this will tell the status of the robot when on init(), which will be "initialized"
 }

//------------------------------------------------------------------------------------------------------------------------------------------ 
                                            //this is the main code that will loop once the robot is started
    //Loop on Start
    @Override
    public void loop() {

     float rightStickY = gamepad1.right_stick_y;     //define the right stick's y axis
     float leftStickY = gamepad1.left_stick_y;       //define the left stick's y axis
     



        robot.motor1.setPower(leftStickY);            //tells motor1 power to be whatever the left stick's y is
        robot.motor2.setPower(-rightStickY);            //tells motor2 power to be whatever the right stick's y is
        
        
        

        telemetry.addData("motor1 Power", robot.motor1.getPower());     //this will tell the phone to display the power level of motor1
        telemetry.addData("motor2 Power", robot.motor2.getPower());     //this will tell the phone to display the power level of motor2
        telemetry.addData("motor3 Power", robot.motor3.getPower());
        //COLOR SENSOR

        robot.colorSensor.red();
        robot.colorSensor.blue();
        robot.colorSensor.green();
        robot.colorSensor.alpha();


        if (robot.colorSensor.red() > robot.colorSensor.blue()) {
            robot.motor3.setPower(1);
        } else if (robot.colorSensor.green() > robot.colorSensor.red()) {
            if (robot.colorSensor.green() > robot.colorSensor.blue()) {
            robot.motor3.setPower(0.5);
            } else {
                robot.motor3.setPower(0);
            }
        } else {
            robot.motor3.setPower(0);
        }

        telemetry.addData("Red", robot.colorSensor.red());
        telemetry.addData("Blue", robot.colorSensor.blue());
        telemetry.addData("Green", robot.colorSensor.green());
        telemetry.addData("Alpha", robot.colorSensor.alpha());
        
        
        //DISTANCE SENSOR
        robot.sensorRange.getDistance(DistanceUnit.INCH);
        telemetry.addData("Distance:", robot.sensorRange.getDistance(DistanceUnit.INCH));
    }

//------------------------------------------------------------------------------------------------------------------------------------------ 
                                            //this isn't currently being used in this program
   //run once on stop
    @Override
    public void stop() {
    }
}

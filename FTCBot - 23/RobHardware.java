
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.util.Hardware;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.DistanceSensor;

public class RobHardware
{
    // Instantiate Motors
    public DcMotor motor1;
    public DcMotor motor2;
    public DcMotor motor3;
    public DcMotor motor4;
    public ColorSensor colorSensor;

    public DistanceSensor sensorRange;


    //Create Hardware map
    HardwareMap hardwareMap;


    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap hardwareMap) {


        // Define Motors
    motor1 = hardwareMap.get(DcMotor.class, "motor1");
    motor2 = hardwareMap.get(DcMotor.class, "motor2");   
    motor3 = hardwareMap.get(DcMotor.class, "motor3"); 
    motor4 = hardwareMap.get(DcMotor.class, "motor4");     
    
    // Set motor power    
       motor1.setPower(0);
       motor2.setPower(0);
       motor3.setPower(0);
       motor4.setPower(0);
        
   
 
    // set motor mode

    //Motor power to zero
    
    motor1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    motor2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    motor3.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
    

    }
 }

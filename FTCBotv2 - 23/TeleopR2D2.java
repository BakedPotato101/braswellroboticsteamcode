// Import necessary libraries
package org.firstinspires.ftc.teamcode.TestCode1;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Blinker;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode; 

@TeleOp

public class Test4 extends LinearOpMode {
    private Blinker control_Hub;
    private Blinker expansion_Hub_2;
    private IMU imu;
    private DcMotor motor1;
    private DcMotor motor2;
    private DcMotor motor3;
    private DcMotor motor4;
    
    

    @Override
    public void runOpMode() {
        motor1 = hardwareMap.get(DcMotor.class,"motor1");
        
        telemetry.addData("Status", "Initionalized");
        telemetry.update(); 
        
        waitForStart(); 
        
        while (opModeIsActive()) {
            
            telemetry.addData("Robot", "Running"); 
            telemetry.update(); 
            
            motor1.setPower(1);
            
        }
        
    }
    
    
}
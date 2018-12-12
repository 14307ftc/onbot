package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.robotcore.external.Const;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import org.firstinspires.ftc.teamcode.Constants;

/*
* THIS IS AN OPMODE.
* This is an Autonomous program meant for the OUTER lane from the crater side.
*/

@Autonomous


public class AutoCrater1 extends LinearOpMode{
    
    //Defining Hardware
    private DcMotor fLDC;
    private DcMotor fRDC;
    private DcMotor bLDC;
    private DcMotor bRDC;
    private DcMotor hook;
    private Servo claim;
    
    //What is actually running
    public void runOpMode() {
        
        //Initiating hardware
        bLDC = hardwareMap.get(DcMotor.class, Constants.leftDriveBack);
        bRDC = hardwareMap.get(DcMotor.class, Constants.rightDriveBack);
        fLDC = hardwareMap.get(DcMotor.class, Constants.leftDriveFront);
        fRDC = hardwareMap.get(DcMotor.class, Constants.rightDriveFront);
        hook = hardwareMap.get(DcMotor.class, Constants.hook);
        claim = hardwareMap.get(Servo.class, Constants.claim);
    
    
        waitForStart();
        
        while (opModeIsActive()) {
            time = 0;
            //Moving with time
            telemetry.addData("StartingTime", time);
            telemetry.addLine();
            claim.setPosition(0.5);
            //Moving the hook
            MoveHook(2.7);
            Wait(1);
                
            //Micromanaging position
            turn(25,"left");
            Wait(0.5);
            drive(2, "forward");
            Wait(0.5);
            turn(25, "right");
            
            //drive to lane 1
            drive(30, "forward");
            Wait(0.2);
            turn(90, "left");
            Wait(0.2);
            drive(38, "forward");
            Wait(0.2);
            turn(45, "left");
            Wait(0.2);
            
            //drop marker
            drive(66, "forward");
            Wait(0.2);
            ServoFaceUp();
            Wait(1);
            ServoFaceDown();
            Wait(1);
            
            //go to crater
            turn(8, "right");
            Wait(0.2);
            drive(120, "backward");
            Wait(1);
            stop();
            telemetry.addData("FinishTime", time);
            telemetry.update();
        }
    }
    
    //Moves hook
    public void MoveHook(double timeneeded) {
        time = 0;
        resetStartTime();
        while (time<=timeneeded) {
            hook.setPower(1);
        }
        hook.setPower(0);
    }
    
    //Driving the robot (enter diastance to move forward in inches and direction forward or backward)
    public void drive(double distance, String direction) {
        // check the direction
        double power=0;
        if (direction=="forward") {
            power = 0.6;
        } else if (direction == "backward") {
            power = -0.6;
        }
        // calculate time needed
        double speed = Constants.unispeed;
        double timeneeded = 0;
        timeneeded = distance/speed;
        time = 0;
        resetStartTime();
        // set power by direction
        while (time<=timeneeded) {
            fLDC.setPower(-power);
            fRDC.setPower(power);
            bLDC.setPower(-power);
            bRDC.setPower(power);
        }
        Nothing();
    }
    
    //Turning the robot (enter amount of degrees to turn and direction to turn(left or right))
    public void turn(double degrees, String direction) {
        // check the direction
        double power=0;
        if (direction=="left") {
            power = 0.6;
        } else if (direction == "right") {
            power = -0.6;
        }
        // calculate time needed
        double speed =  Constants.uniturn;
        double timeneeded = 0;
        timeneeded = degrees/speed;
        time = 0;
        resetStartTime();
        // set power by direction
        while (time<=timeneeded) {
            fLDC.setPower(power);
            fRDC.setPower(power);
            bLDC.setPower(power);
            bRDC.setPower(power);
        }
        Nothing();
    }
    
    //Wait for the amount of time entered
    public void Wait(double amount) {
        time = 0;
        resetStartTime();
        while (time<=amount) {
            fLDC.setPower(0);
            fRDC.setPower(0);
            bLDC.setPower(0);
            bRDC.setPower(0);   
        }
    }
    
    //Do nothing
    public void Nothing() {
        fLDC.setPower(0);
            fRDC.setPower(0);
            bLDC.setPower(0);
            bRDC.setPower(0);
    }
    
    //Servo Methods
    public void ServoFaceUp(){
        claim.setPosition(0);
    }
    public void ServoFaceDown(){
        claim.setPosition(1);
    }
    
}

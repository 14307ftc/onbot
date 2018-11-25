package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous


public class My503AutoCrater extends LinearOpMode{
    
    //Define hardware
    private Servo claim;
    private DcMotor fLDC;
    private DcMotor fRDC;
    private DcMotor bLDC;
    private DcMotor bRDC;

    public void runOpMode() {
        
        //Init hardware
        bLDC = hardwareMap.get(DcMotor.class, "bLDC");
        bRDC = hardwareMap.get(DcMotor.class, "bRDC");
        fLDC = hardwareMap.get(DcMotor.class, "fLDC");
        fRDC = hardwareMap.get(DcMotor.class, "fRDC");
        claim = hardwareMap.get(Servo.class, "claim");
        
        waitForStart();
        while (opModeIsActive()) {
        //Moving with time
        resetStartTime();
        
        while (time<0.7) {
            DriveForward();   
        } 
        
        while (time>0.7 && time<1.4) {
            TurnLeft();
        }
        
        while (time>1.4 && time<2.9) {
            DriveForward();
        }
        
        while (time>2.9 && time<3.25) {
            TurnLeft();
        }
        
        while (time>3.25 && time<4.5) {
            DriveForward();
        }
        stop();
        
        while (time>4.5) {
            ServoFaceDown(); 
        }
    }
    }
    
    //Drive Methods
    public void DriveForward() {
    
        fLDC.setPower(1);
        fRDC.setPower(-1);
        bLDC.setPower(1);
        bRDC.setPower(-1);
    }
    
    public void DriveBackward() {
    
        fLDC.setPower(-1);
        fRDC.setPower(1);
        bLDC.setPower(-1);
        bRDC.setPower(1);
    }
    
    public void TurnLeft() {
        fLDC.setPower(1);
        fRDC.setPower(1);
        bLDC.setPower(1);
        bRDC.setPower(1);
    }
    
    public void TurnRight() {
        fLDC.setPower(-1);
        fRDC.setPower(-1);
        bLDC.setPower(-1);
        bRDC.setPower(-1);
    }
    
    public void Stop() {
    
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

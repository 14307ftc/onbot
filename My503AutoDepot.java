package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous


public class My503AutoDepot extends LinearOpMode{
    
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
        telemetry.addData("StartingTime", time);
        telemetry.addLine();
        ServoFaceDown();
        
        while (time<1) {
            DriveForward();
        } 
        stop();
        telemetry.addData("FinishTime", time);
        telemetry.update();
        
        /*while (time>1) {
            ServoFaceUp(); 
        }*/
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

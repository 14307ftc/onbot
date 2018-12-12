package org.firstinspires.ftc.teamcode14307;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import org.firstinspires.ftc.teamcode.Constants;

@Autonomous


public class OneSecondTest extends LinearOpMode{
    
    //Defining Hardware
    private DcMotor fLDC;
    private DcMotor fRDC;
    private DcMotor bLDC;
    private DcMotor bRDC;
    private DcMotor hook;
    private Servo claim;
    
    public void runOpMode() {
        
        //Initiating hardware
        bLDC = hardwareMap.get(DcMotor.class, Constants.leftDriveBack);
        bRDC = hardwareMap.get(DcMotor.class, Constants.rightDriveBack);
        fLDC = hardwareMap.get(DcMotor.class, Constants.leftDriveFront);
        fRDC = hardwareMap.get(DcMotor.class, Constants.rightDriveFront);
        hook = hardwareMap.get(DcMotor.class, Constants.hook);
        claim = hardwareMap.get(Servo.class, "claim");
    
    
    waitForStart();
    
    while (opModeIsActive()) {
        time = 0;
        resetStartTime();
        //Moving with time
        telemetry.addData("StartingTime", time);
        telemetry.addLine();
        /*while (time<=1) {
        DriveForward();   
        }*/
        Wait(1);
        time = 0;
        resetStartTime();
        while (time<=1) {
        TurnRight();   
        }
        Wait(1);
        resetStartTime();
        stop();
        telemetry.addData("FinishTime", time);
        telemetry.update();
        
    }
    
    }
    
    public void DriveForward() {
    
        fLDC.setPower(-0.6);
        fRDC.setPower(0.6);
        bLDC.setPower(-0.6);
        bRDC.setPower(0.6);
    }
    
    public void DriveBackward() {
    
        fLDC.setPower(1);
        fRDC.setPower(-1);
        bLDC.setPower(1);
        bRDC.setPower(-1);
    }
    
    public void TurnRight() {
        fLDC.setPower(-0.6);
        fRDC.setPower(-0.6);
        bLDC.setPower(-0.6);
        bRDC.setPower(-0.6);
    }
    
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
    public void TurnLeft() {
        fLDC.setPower(0.6);
        fRDC.setPower(0.6);
        bLDC.setPower(0.6);
        bRDC.setPower(0.6);
    }
}

/*package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous

public class My503AutoDepot_Copy extends LinearOpMode{
    
    //Define hardware
    private Servo claim;
    private DcMotor fLDC;
    private DcMotor fRDC;
    private DcMotor bLDC;
    private DcMotor bRDC;

    public void runOpMode() throws InterruptedException{
        
        //Init hardware
        bLDC = hardwareMap.get(DcMotor.class, "bLDC");
        bRDC = hardwareMap.get(DcMotor.class, "bRDC");
        fLDC = hardwareMap.get(DcMotor.class, "fLDC");
        fRDC = hardwareMap.get(DcMotor.class, "fRDC");
        claim = hardwareMap.get(Servo.class, "claim");
        
        //ElapsedTime et = new ElapsedTime();
        
        //Moving with time
        //resetStartTime();
        time = 0;
        //et.now = 0;
        
        telemetry.addData("Time0", time);
        telemetry.addLine();
        
        //ServoFaceDown();
        
        while (time<1) {
            DriveForward();
        }
        telemetry.addData("Time1", time);
        telemetry.addLine();
//        double x= 0;
 //       while (x<10000) {
   //         x=x+.0001;
     //   }
        sleep(9000);
        telemetry.addData("Time2", time);
        telemetry.addLine();
        while (time>10.01 && time<11) {
            telemetry.addData("Time3", time);
            telemetry.addLine();
            DriveForward();
        }
    telemetry.addData("Time4", time);
        telemetry.update();
        time = 0;
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
    
    public void Wait() {
    
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
}*/

package org.firstinspires.ftc.teamcode;

import android.app.Activity;
import com.qualcomm.robotcore.hardware.ColorSensor;
import android.graphics.Color;
import android.view.View;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous(name = "MRColorSense", group = "Sensor")
public class MRColorSense extends LinearOpMode {

com.qualcomm.robotcore.hardware.ColorSensor sensorColor;
private Servo claim;
private DcMotor fLDC;
private DcMotor fRDC;
private DcMotor bLDC;
private DcMotor bRDC;
  

  public void runOpMode() {
    
    sensorColor = hardwareMap.colorSensor.get("sensorColor");
    bLDC = hardwareMap.get(DcMotor.class, "bLDC");
    bRDC = hardwareMap.get(DcMotor.class, "bRDC");
    fLDC = hardwareMap.get(DcMotor.class, "fLDC");
    fRDC = hardwareMap.get(DcMotor.class, "fRDC");
    claim = hardwareMap.get(Servo.class, "claim");
    
    waitForStart();
    while (opModeIsActive()) {
        float red = sensorColor.red();
        float green = sensorColor.green();
        float blue = sensorColor.blue();
        
        telemetry.addData("Red ", red);
        telemetry.addData("Green ",green);
        telemetry.addData("Blue ", blue);
       
        sensorColor.enableLed(true);
       
        if (isYellow(sensorColor)) {
                //pushYellow();
                telemetry.addData("Color","Yellow");
                telemetry.update(); 
        }
        if (isWhite(sensorColor)) {
                //pushWhite();
                telemetry.addData("Color","White");
                telemetry.update();
        }
        // else {
        //     telemetry.addData("Color","Undetected");
        //     telemetry.update();
        // }

        //telemetry.update();
        }//end while
    }//end method
    
    public boolean isYellow(ColorSensor sensorColor) {
        if(sensorColor.red() >=.1 && sensorColor.red() <=1) {
            if(sensorColor.green() >=.1 && sensorColor.green() <=1) {
                if(sensorColor.blue() >=.1 && sensorColor.blue() <=1) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public void pushYellow() {
        resetStartTime();
        time = 0;
        while (time<0.4) {
            DriveForward(0.75);
        }
        while (time>0.4  && time<0.8) {
            DriveBackward(0.75);
        }
        while (time>0.8 && time<0.9) {
            Stop();
        }
    }
    
    public boolean isWhite(ColorSensor sensorColor) {
        if(sensorColor.red() >=2 && sensorColor.red() <=17 || sensorColor.red() >=21){
            if(sensorColor.green() >=2 && sensorColor.green() <18 || sensorColor.green() >=22){
                if(sensorColor.blue() >=2 || sensorColor.blue() <=19 || sensorColor.blue() >=23){   
                    return true;
               }
            }
        }
        return false;
    }
    
     public void pushWhite() {
        resetStartTime();
        time = 0;
        while (time<0.3) {
            DriveBackward(0.75);
        }
        while (time>0.3 && time<1.0) {
            TurnLeft(0.75);
        }
        while (time>1.0 && time <1.8) {
            DriveForward(0.75);
        }
        while (time>1.8 && time <2.6) {
            TurnRight(0.75);
        }
        while (time>2.6 && time<2.72) {
            DriveForward(0.75);
        }
        while (time>2.72 && time<2.8) {
            Stop();
        }
     }
    
    //Drive Methods
    public void DriveForward(double powerLevel) {
        fLDC.setPower(powerLevel);
        fRDC.setPower(-1*powerLevel);
        bLDC.setPower(powerLevel);
        bRDC.setPower(-1*powerLevel);
    }
    
    /*public void DriveForward() {
    
        fLDC.setPower(1);
        fRDC.setPower(-1);
        bLDC.setPower(1);
        bRDC.setPower(-1);
    }*/

    public void DriveBackward(double powerLevel) {
    
        fLDC.setPower(-1*powerLevel);
        fRDC.setPower(1*powerLevel);
        bLDC.setPower(-1*powerLevel);
        bRDC.setPower(1*powerLevel);
    }
    
    /*public void DriveBackward() {
    
        fLDC.setPower(-1);
        fRDC.setPower(1);
        bLDC.setPower(-1);
        bRDC.setPower(1);
    }*/
    
    public void TurnLeft(double powerLevel) {
        fLDC.setPower(powerLevel);
        fRDC.setPower(powerLevel);
        bLDC.setPower(powerLevel);
        bRDC.setPower(powerLevel);
    }
    
    public void TurnRight(double powerLevel) {
        fLDC.setPower(-powerLevel);
        fRDC.setPower(-powerLevel);
        bLDC.setPower(-powerLevel);
        bRDC.setPower(-powerLevel);
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

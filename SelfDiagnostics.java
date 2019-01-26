package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.robotcore.external.Const;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import org.firstinspires.ftc.teamcode.Constants;

@Autonomous

public class SelfDiagnostics extends LinearOpMode{

    //Wheels
    private DcMotor fLDC;
    private DcMotor fRDC;
    private DcMotor bLDC;
    private DcMotor bRDC;
    
    //Hook
    private DcMotor hook;
    
    //Arm
    private DcMotor armRotator;
    private DcMotor armExtender;
    private CRServo armServo;
    
    //Claim
    private Servo claim;
    
    //What is actually running
    public void runOpMode() {
        
        //Initiating hardware
        bLDC = hardwareMap.get(DcMotor.class, Constants.leftDriveBack);
        bRDC = hardwareMap.get(DcMotor.class, Constants.rightDriveBack);
        fLDC = hardwareMap.get(DcMotor.class, Constants.leftDriveFront);
        fRDC = hardwareMap.get(DcMotor.class, Constants.rightDriveFront);
        hook = hardwareMap.get(DcMotor.class, Constants.hook);
        armRotator = hardwareMap.get(DcMotor.class, Constants.armRotator);
        armExtender = hardwareMap.get(DcMotor.class, Constants.armExtender);
        armServo = hardwareMap.get(CRServo.class, Constants.armServo);
        claim = hardwareMap.get(Servo.class, Constants.claim);
    
        waitForStart();
        
        while (opModeIsActive()) {
            time = 0;
            
            // Wheel Motor Tests
            resetStartTime();
            while (time<=1){
                bLDC.setPower(1);
            }
            bLDC.setPower(0);
            
            while (time>1 && time <=2){
                bRDC.setPower(1);
            }
            bRDC.setPower(0);
            
            while (time>2 && time <=3){
                fLDC.setPower(1);
            }
            fLDC.setPower(0);
            
            while (time>3 && time <=4){
                fRDC.setPower(1);
            }
            fRDC.setPower(0);
           
            // Hook
            while (time>4 && time <=4.5){
                hook.setPower(1);
            }
            hook.setPower(0);
            
            while (time>4.5 && time <=5){
                hook.setPower(-1);
            }
            hook.setPower(0);
            
            // Claim
            claim.setPosition(0);
                while (time>5 && time <=6){
                }
            claim.setPosition(0.5);
                while (time>8 && time <=9){
                }
            claim.setPosition(1);
                
            // Arm Servo
            while (time>9 && time<=10) {
                armServo.setPower(1);
                }
            while (time>10 && time<=11) {
                armServo.setPower(-1);
                }
             
            // Arm Extender
            while (time>11 && time <=11.5){
                armExtender.setPower(1);
            }
            armExtender.setPower(0);
            while (time>11.5 && time <=12){
                armExtender.setPower(-1);
            }
            armExtender.setPower(0);
            
            // Arm Rotator
            while (time>12 && time <=12.5){
                armRotator.setPower(1);
            }
            armRotator.setPower(0);
            while (time>12.5 && time <=13){
                armRotator.setPower(-1);
            }
            armRotator.setPower(0);
        
            stop();
        }
    }   
}

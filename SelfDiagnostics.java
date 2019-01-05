package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
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
            
            //Motor Tests
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
           
            while (time>4 && time <=4.5){
                hook.setPower(1);
            }
            hook.setPower(0);
            
            while (time>4.5 && time <=5){
                hook.setPower(-1);
            }
            hook.setPower(0);
            
            //Servo Tests
            
                claim.setPosition(0);
                while (time>5 && time <=5.1){
                }
                claim.setPosition(0.5);
                while (time>5.1 && time <=5.2){
                }
                claim.setPosition(1);
                stop();
                
        }
    }
}

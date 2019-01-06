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
* This is an Autonomous program meant for lifting up the hook.
* Make sure the robot is being lifted or raised up off the ground to prevent damage to the hook.
*/

@Autonomous


public class HookUp extends LinearOpMode{
    
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
            
            //Moving the hook
            MoveHook(4.5);
            stop();
            telemetry.addData("FinishTime", time);
            telemetry.update();
        }
    }
    
    //Moves hook
    public void MoveHook(double timeneeded) {
        Nothing();
        time = 0;
        resetStartTime();
        while (time<=timeneeded) {
            hook.setPower(-1);
        }
        hook.setPower(0);
        Nothing();
    }
    //Do nothing
    public void Nothing() {
        fLDC.setPower(0);
            fRDC.setPower(0);
            bLDC.setPower(0);
            bRDC.setPower(0);
            hook.setPower(0);
    }
}

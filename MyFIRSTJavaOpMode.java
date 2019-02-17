package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.robotcore.external.Const;
import org.firstinspires.ftc.teamcode.Constants;

import java.lang.annotation.Target;
import com.qualcomm.robotcore.hardware.Gamepad;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import com.qualcomm.robotcore.hardware.Gyroscope;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp
public class MyFIRSTJavaOpMode extends LinearOpMode {
    private DcMotor bLDC;
    private DcMotor bRDC;
    private DcMotor hook;
    //private DcMotor armCollect;
    private DcMotor armDeposit;
    //private DcMotor armSpinner;
    //private Servo claim;
    
    
    @Override
    public void runOpMode() {
        bLDC = hardwareMap.get(DcMotor.class, Constants.leftDriveBack);
        bRDC = hardwareMap.get(DcMotor.class, Constants.rightDriveBack);
        hook = hardwareMap.get(DcMotor.class, Constants.hook);
        //armCollect = hardwareMap.get(DcMotor.class, Constants.armCollect);
        armDeposit = hardwareMap.get(DcMotor.class, Constants.armDeposit);
        //armSpinner = hardwareMap.get(DcMotor.class, Constants.armSpinner);
        //claim = hardwareMap.get(Servo.class, Constants.claim);
        
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        
        double tgtPower;
        
        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        
        // Run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            
            // Gamepad 1- left stick makes it go forward and backward   
            tgtPower = -this.gamepad1.left_stick_y;
            bLDC.setPower(-tgtPower);
            bRDC.setPower(tgtPower);
            
            
            
            // Gamepad 1- right stick makes it go left and right 
            tgtPower = -this.gamepad1.right_stick_x;
            bLDC.setPower(tgtPower);
            bRDC.setPower(tgtPower);
            
            
            // Gamepad 1- hook moved by 'a' and 'y'
            while (gamepad2.dpad_up) {
                hook.setPower(1);
            }
            if (gamepad2.left_bumper || gamepad2.right_bumper) {
                resetStartTime();
                    hook.setPower(0);
            }
            while (gamepad2.dpad_down) {
                hook.setPower(-1);
            }
            
            // Gamepad 1- buttons to move claim servo
            /*if(gamepad1.y) {
                claim.setPosition(1);
            }
            
            if(gamepad1.a) {
                claim.setPosition(0);
            }*/
            
            // Gamepad 2- buttons to move Motor Spinner
            /*if(gamepad2.a) {
                resetStartTime();
                    armSpinner.setPower(1);
            }
            
            if(gamepad2.x || gamepad2.b) {
                resetStartTime();
                    armSpinner.setPower(0);
            }
            
            if(gamepad2.y) {
                resetStartTime();
                    armSpinner.setPower(-1);
            }*/
            
            // Gamepad 2- right stick makes it go up and down
            tgtPower = -this.gamepad2.right_stick_y;
            armDeposit.setPower(-tgtPower*6/10);
            
            // Gamepad 2- left stick makes it go up and down
            /*double collectpower = -this.gamepad2.left_stick_y;
            if (collectpower >= 0) {
                armCollect.setPower(collectpower*3/10);
            } else {
                armCollect.setPower(collectpower*6/10);
            }*/
            
            telemetry.addData("Target Power", tgtPower);
            //telemetry.addData("Back Left", bLDC.getPower());
            telemetry.addData("Back Right", bRDC.getPower());
            telemetry.addData("Hook", hook.getPower());
            telemetry.addData("Arm Deposit", armDeposit.getPower());
            //telemetry.addData("Arm Collect", armCollect.getPower());
            telemetry.addData("Status", "Running");

            telemetry.update();
        }
    }
}   

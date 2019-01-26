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
    private DcMotor fLDC;
    private DcMotor fRDC;
    private DcMotor hook;
    private DcMotor armExtender;
    private DcMotor armRotator;
    private CRServo armServo;
    private Servo claim;
    
    
    @Override
    public void runOpMode() {
        bLDC = hardwareMap.get(DcMotor.class, Constants.leftDriveBack);
        bRDC = hardwareMap.get(DcMotor.class, Constants.rightDriveBack);
        fLDC = hardwareMap.get(DcMotor.class, Constants.leftDriveFront);
        fRDC = hardwareMap.get(DcMotor.class, Constants.rightDriveFront);
        hook = hardwareMap.get(DcMotor.class, Constants.hook);
        armExtender = hardwareMap.get(DcMotor.class, Constants.armExtender);
        armRotator = hardwareMap.get(DcMotor.class, Constants.armRotator);
        armServo = hardwareMap.get(CRServo.class, Constants.armServo);
        claim = hardwareMap.get(Servo.class, Constants.claim);
        
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
            fLDC.setPower(-tgtPower);
            fRDC.setPower(tgtPower);
            
            
            // Gamepad 1- right stick makes it go left and right 
            tgtPower = -this.gamepad1.right_stick_x;
            bLDC.setPower(tgtPower);
            bRDC.setPower(tgtPower);
            fLDC.setPower(tgtPower);
            fRDC.setPower(tgtPower);
            
            // Gamepad 1- left trigger pulls out linear slide
            tgtPower = -this.gamepad1.left_trigger;
            armExtender.setPower(tgtPower*4/10);
            
            // Gamepad 1- buttons to move claim servo
            if(gamepad1.y) {
                claim.setPosition(1);
            }
            
            if(gamepad1.a) {
                claim.setPosition(0);
            }
            
            // Gamepad 2- buttons to move CR Servo
            if(gamepad2.y) {
                resetStartTime();
                    armServo.setPower(1);
            }
            
            if(gamepad2.x || gamepad2.b) {
                resetStartTime();
                    armServo.setPower(0);
            }
            
            if(gamepad2.a) {
                resetStartTime();
                    armServo.setPower(-1);
            }
            
            // Gamepad 2- right stick makes it go up and down
            tgtPower = -this.gamepad2.right_stick_y;
            hook.setPower(tgtPower);
            
            // Gamepad 2- left stick makes it go up and down
            tgtPower = -this.gamepad2.left_stick_y;
            armRotator.setPower(tgtPower*45/100);
            
            telemetry.addData("Target Power", tgtPower);
            telemetry.addData("Back Left", bLDC.getPower());
            telemetry.addData("Back Right", bRDC.getPower());
            telemetry.addData("Front Left", fLDC.getPower());
            telemetry.addData("Front Right", fRDC.getPower());
            telemetry.addData("Hook", hook.getPower());
            telemetry.addData("Arm Rotator", armRotator.getPower());
            telemetry.addData("Arm Extender", armExtender.getPower());
            telemetry.addData("Status", "Running");

            telemetry.update();
        }
    }
}   

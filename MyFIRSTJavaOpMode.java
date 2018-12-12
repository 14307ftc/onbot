package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
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
    private DcMotor arm;
    private DcMotor arm2;
    private Servo claim;
    
    @Override
    public void runOpMode() {
        bLDC = hardwareMap.get(DcMotor.class, Constants.leftDriveBack);
        bRDC = hardwareMap.get(DcMotor.class, Constants.rightDriveBack);
        fLDC = hardwareMap.get(DcMotor.class, Constants.leftDriveFront);
        fRDC = hardwareMap.get(DcMotor.class, Constants.rightDriveFront);
        hook = hardwareMap.get(DcMotor.class, Constants.hook);
        arm = hardwareMap.get(DcMotor.class, "arm");
        arm2 = hardwareMap.get(DcMotor.class, "arm2");
        claim = hardwareMap.get(Servo.class, "claim");
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        double tgtPower = 0;
        double powers = 0;
        while (opModeIsActive()) {
            // left stick makes it go forward and backward   
            tgtPower = -this.gamepad1.left_stick_y;
            bLDC.setPower(-tgtPower*6/10);
            bRDC.setPower(tgtPower*6/10);
            fLDC.setPower(-tgtPower*6/10);
            fRDC.setPower(tgtPower*6/10);
            
            telemetry.addData("Target Power", tgtPower);
            telemetry.addData("Back Left", bRDC.getPower());
            telemetry.addData("Back Right", bLDC.getPower());
            telemetry.addData("Status", "Running");
            telemetry.update();
            
            
            //right stick goes forward and backw    
            tgtPower = -this.gamepad1.right_stick_x;
            bLDC.setPower(tgtPower*6/10);
            bRDC.setPower(tgtPower*6/10);
            fLDC.setPower(tgtPower*6/10);
            fRDC.setPower(tgtPower*6/10);
            
            tgtPower = -this.gamepad2.left_stick_y;
            hook.setPower(tgtPower);
            
            tgtPower = -this.gamepad2.right_stick_x;//left_trigger;
            arm.setPower(tgtPower*0.35);
            
            tgtPower = -this.gamepad2.left_stick_x;//left_trigger;
            arm2.setPower(tgtPower*0.35);
            
            telemetry.addData("Target Power", tgtPower);
            telemetry.addData("Back Left", bLDC.getPower());
            telemetry.addData("Back Right", bRDC.getPower());
            telemetry.addData("Front Left", fLDC.getPower());
            telemetry.addData("Front Right", fRDC.getPower());
            telemetry.addData("Status", "Running");

            telemetry.update();
        }
    }
}   

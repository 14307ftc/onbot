package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
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
    
    @Override
    public void runOpMode() {
        bLDC = hardwareMap.get(DcMotor.class, "bLDC");
        bRDC = hardwareMap.get(DcMotor.class, "bRDC");
        fLDC = hardwareMap.get(DcMotor.class, "fLDC");
        fRDC = hardwareMap.get(DcMotor.class, "fRDC");
        
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        double tgtPower = 0;
        while (opModeIsActive()) {
        // left stick makes it go forward and backward   
            tgtPower = -this.gamepad1.left_stick_y;
            bLDC.setPower(-tgtPower);
            bRDC.setPower(tgtPower);
            fLDC.setPower(-tgtPower);
            fRDC.setPower(tgtPower);
            
            telemetry.addData("Target Power", tgtPower);
            telemetry.addData("Back Left", bRDC.getPower());
            telemetry.addData("Back Right", bLDC.getPower());
            telemetry.addData("Status", "Running");
            telemetry.update();
            
            
        //right stick goes forward and backw    
            tgtPower = -this.gamepad1.right_stick_x;
            bLDC.setPower(tgtPower);
            bRDC.setPower(tgtPower);
            fLDC.setPower(tgtPower);
            fRDC.setPower(tgtPower);
            
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

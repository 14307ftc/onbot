package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.HardwarePushbotIntitialize14307;
@Autonomous


public class AutoDepot extends LinearOpMode{
    
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
        claim = hardwareMap.get(Servo.class, Constants.claim);
        //HardwarePushbotIntitialize14307 robot = new HardwarePushbotIntitialize14307();
    
        waitForStart();
        
        while (opModeIsActive()) {
            
            time = 0;
            resetStartTime();
            //Moving with time
            telemetry.addData("StartingTime", time);
            telemetry.addLine();
            claim.setPosition(0.5);
            //Move the hook
            MoveHook(4.45);
            Wait(1);
            
            //Micromanaging position
            turn(25,"left");
            Wait(0.5);
            drive(2, "forward");
            Wait(0.5);
            turn(32, "right");
            
            //Going to depot to drop marker
            drive(62,"forward");
            Wait(1);
            ServoFaceUp();
            Wait(0.2);
            ServoFaceDown();
            Wait(0.5);
            drive(3, "forward");
            Wait(0.25);
            drive(4, "backward");
            Wait(0.25);
            
            //Heading into the crater
    
            turn(38, "right");
            Wait(1);
            drive(110, "backward");
            Wait(1);
            telemetry.addData("stopRobot", time);
            telemetry.update();
            Nothing();
            stop();
            telemetry.addData("FinishTime", time);
            telemetry.update();
            //ready to start next to the crater
        }
    }
    
    public void MoveHook(double timeneeded) {
        time = 0;
        resetStartTime();
        while (time<=timeneeded) {
            hook.setPower(1);
        }
        hook.setPower(0);
    }
        
    // Driving the robot (enter diastance to move forward in inches and direction forward or backward)
    
    public void drive(double distance, String direction) {
        Nothing();
    
        // check the direction
    
        double power=0;
        if (direction=="forward") {
            power = 0.4;
        } else if (direction == "backward") {
            power = -0.4;
        }
        // calculate time needed
        double speed = Constants.unispeed;
        double timeneeded = 0;
        timeneeded = distance/speed;
        time = 0;
        resetStartTime();
        // set power by direction
        while (time<=timeneeded) {
            fLDC.setPower(-power);
            fRDC.setPower(power);
            bLDC.setPower(-power);
            bRDC.setPower(power);
        }
        Nothing();
    }
        //Turning the robot (enter amount of degrees to turn and direction to turn(left or right))
    public void turn(double degrees, String direction) {
        Nothing();
        // check the direction
        double power=0;
        if (direction=="left") {
            power = 0.4;
        } else if (direction == "right") {
            power = -0.4;
        }
        // calculate time needed
        double speed = Constants.uniturn;
        double timeneeded = 0;
        timeneeded = degrees/speed;
        time = 0;
        resetStartTime();
        // set power by direction
        while (time<=timeneeded) {
            fLDC.setPower(power);
            fRDC.setPower(power);
            bLDC.setPower(power);
            bRDC.setPower(power);
        }
        Nothing();
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
    public void Nothing() {
        fLDC.setPower(0);
        fRDC.setPower(0);
        bLDC.setPower(0);
        bRDC.setPower(0);
    }
    //Servo Methods
    public void ServoFaceDown(){
        claim.setPosition(0);
    }
    public void ServoFaceUp(){
        claim.setPosition(1);
    }
    
    
}

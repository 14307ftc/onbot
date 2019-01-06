package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.Constants;

/**
 * This is NOT an opmode.
 *
 * This class can be used to define all the specific hardware for a single robot.
 * In this case that robot is a Pushbot.
 * See PushbotTeleopTank_Iterative and others classes starting with "Pushbot" for usage examples.
 *
 * This hardware class assumes the following device names have been configured on the robot:
 * Note:  All names are lower case and some have single spaces between words.
 *
 * Motor channel:  Left  drive motor:        "left_drive"
 * Motor channel:  Right drive motor:        "right_drive"
 * Motor channel:  Manipulator drive motor:  "left_arm"
 * Servo channel:  Servo to open left claw:  "left_hand"
 * Servo channel:  Servo to open right claw: "right_hand"
 */
public class HardwarePushbotIntitialize14307 extends LinearOpMode
{
    //Defining Hardware
    private DcMotor fLDC;
    private DcMotor fRDC;
    private DcMotor bLDC;
    private DcMotor bRDC;
    private DcMotor hook;
    private Servo claim;
    
        public void runOpMode(){
            
        }
    /* local OpMode members. */
    private ElapsedTime period  = new ElapsedTime();

    /* Constructor */
    public void HardwarePushbotIntitialize14307(){
        //Initiating hardware
        bLDC = hardwareMap.get(DcMotor.class, Constants.leftDriveBack);
        bRDC = hardwareMap.get(DcMotor.class, Constants.rightDriveBack);
        fLDC = hardwareMap.get(DcMotor.class, Constants.leftDriveFront);
        fRDC = hardwareMap.get(DcMotor.class, Constants.rightDriveFront);
        hook = hardwareMap.get(DcMotor.class, Constants.hook);
        claim = hardwareMap.get(Servo.class, Constants.claim);
    }

    /*Initialize standard Hardware interfaces 
    public void init(HardwareMap hwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;

        // Define and Initialize Motors
        leftDriveFront  = hwMap.get(DcMotor.class, Constants.leftDriveFront);
       leftDriveBack  = hwMap.get(DcMotor.class, Constants.leftDriveBack);

        rightDriveFront = hwMap.get(DcMotor.class, Constants.rightDriveFront);
        rightDriveBack = hwMap.get(DcMotor.class, Constants.rightDriveBack);
        servo = hwMap.get(Servo.class, "servo");
    }*/
 }


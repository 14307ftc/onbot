package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class HardwareInitializer {


    /* Public OpMode members. */

    // Motors

    public DcMotor  leftDriveFront  = null;
    public DcMotor  leftDriveBack   = null;
    public DcMotor  rightDriveFront = null;
    public DcMotor  rightDriveBack  = null;
    public DcMotor  hook            = null;
    public DcMotor  armDeposit      = null;
    
   //Servo

    public Servo    markerHolder   = null;

    public static final double MID_SERVO       =  0.5 ;
    public static final double ARM_UP_POWER    =  0.45 ;
    public static final double ARM_DOWN_POWER  = -0.45 ;

    /* local OpMode members. */

    private ElapsedTime period  = new ElapsedTime();

    HardwareMap hwMap           =  null;
    /* Constructor */
    public HardwareInitializer(){

    }

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;

        // Define and Initialize Motors
/*        leftDriveBack  = hwMap.get(DcMotor.class, Constants.leftDriveBack);
        rightDriveBack = hwMap.get(DcMotor.class,Constants.rightDriveBack);
        hook = hwMap.get(DcMotor.class,Constants.hook);
        armDeposit = hwMap.get(DcMotor.class,Constants.armDeposit);
        // leftDriveFront.setDirection(DcMotor.Direction.FORWARD); // Set to REVERSE if using AndyMark motors
        //  rightDriveFront.setDirection(DcMotor.Direction.REVERSE);// Set to FORWARD if using AndyMark motors

        // Set all motors to zero power
        // Define and Initialize Motors
        leftDriveFront.setDirection(DcMotor.Direction.REVERSE); // Set to REVERSE if using AndyMark motors
        leftDriveBack.setDirection(DcMotor.Direction.REVERSE);// Set to FORWARD if using AndyMark motors
        rightDriveFront.setDirection(DcMotor.Direction.FORWARD); // Set to REVERSE if using AndyMark motors
        rightDriveBack.setDirection(DcMotor.Direction.FORWARD);// Set to FORWARD if using AndyMark motors
        armDeposit.setDirection(DcMotor.Direction.FORWARD);// Set to FORWARD if using AndyMark motors
        // Set all motors to zero power
        leftDriveFront.setPower(0);
        leftDriveBack.setPower(0);
        rightDriveFront.setPower(0);
        rightDriveBack.setPower(0);*/
    }

}

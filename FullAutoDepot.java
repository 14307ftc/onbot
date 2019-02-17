package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import org.firstinspires.ftc.teamcode.Constants;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;

import java.util.List;

@Autonomous


public class FullAutoDepot extends LinearOpMode{
    
    //Defining Hardware
    private DcMotor bLDC;
    private DcMotor bRDC;
    private DcMotor hook;
    private Servo claim;
    
    
    //For Mineral Sensing
    private static final String TFOD_MODEL_ASSET = "RoverRuckus.tflite";
    private static final String LABEL_GOLD_MINERAL = "Gold Mineral";
    private static final String LABEL_SILVER_MINERAL = "Silver Mineral";
    private static final String VUFORIA_KEY = "AWC+eTP/////AAABmVFM7Rg0bUFwvGmDUhcT2tOLaMdN9n9i28A/yKKpI2xh17wN0o9U+CrjY0WkP/nROG2bBfy9Ew/V06Z5d+ht93k0C9pa8wIKPNkSSfiIRmlkUo7FXf5hKM2W7RfLyoSlMA4jcqZL0gY5LrbQpnB07QVLO9hyd7XuVvBHxul8FCFU1fBHfuZTEHbg13F7wpCl6XtZXHHRyGbgJrnaM4RnYy8QXypI72RjHQo/jU+Lqz6qDkYl2kT21pvkMXGn1g/0efSj6Ichd5VYdr6Au6aP3OQayKFHxMgh3XzjKCgKvPYap3rIJWQk6jSL7cedkuxJ/SbBk6vNvVLvC30kntRKM53rD6UqFR6f7X/ABdDwX40W";
    private VuforiaLocalizer vuforia;
    private TFObjectDetector tfod;
    
    private ElapsedTime runtime = new ElapsedTime();
    
    public void runOpMode() {
        
        //Initiating hardware
        bLDC = hardwareMap.get(DcMotor.class, Constants.leftDriveBack);
        bRDC = hardwareMap.get(DcMotor.class, Constants.rightDriveBack);
        hook = hardwareMap.get(DcMotor.class, Constants.hook);
        claim = hardwareMap.get(Servo.class, Constants.claim);
        //HardwarePushbotIntitialize14307 robot = new HardwarePushbotIntitialize14307();
        
        /** Wait for the game to begin */
        telemetry.addData(">", "Press Play to start tracking");
        telemetry.update();
        
        waitForStart();
        
        while (opModeIsActive()) {
            
            time = 0;
            resetStartTime();
            //Moving with time
            telemetry.addData("StartingTime", time);
            telemetry.addLine();
            claim.setPosition(1);
            
            //Move the hook
            MoveHook(1, 1);
            MoveHook(3.6, 0.8);
            Wait(1);
            
            //Micromanaging position
            turn(25,"left");
            Wait(0.5);
            drive(3, "forward");
            Wait(0.5);
            turn(29, "right");
            Wait(0.2);
            MoveHook(0.2, 0.8);
            drive(1, "backward");
            
            //Sampling initiation
            initVuforia();
            
            if (ClassFactory.getInstance().canCreateTFObjectDetector()) {
                initTfod();
            } else {
                telemetry.addData("Sorry!", "This device is not compatible with TFOD");
            }
            
            //Wait(1.5);
            
            //MINERAL SAMPLING HERE
            if (tfod != null) {
                tfod.activate();
            }

            int position=findGoldPosition(2.0);
            if (tfod != null) {
                tfod.shutdown();
            }

          switch(position){
                //Gold Position Left --1  Center --2 Right ---3
                case 1:
                    telemetry.addData("Moving", "Left");
                    turn(22,"left");
                    Wait(.2);
                    drive(49, "forward");
                    Wait(.2);
                    turn(56, "right");
                    Wait(.2);
                    drive(35, "forward");
                    ServoFaceDown();
                    Wait(1);
                    claim.setPosition(0.75);
                    Wait(0.2);
                    drive(59, "backward", 1);
                    break;
                    
              default:        
              case 2:
                    telemetry.addData("Moving", "Center");
                    turn(4, "right");
                    Wait(0.2);
                    drive(70, "forward");
                    Wait(0.5);
                    turn(38, "right");
                    
                     drive(2, "backward");
                    ServoFaceDown();
                    Wait(0.5);
                    claim.setPosition(0.75);
                     drive(62, "backward", 1);
                    break;
                  
              case 3:
                    telemetry.addData("Moving", "Right");
                    turn(30,"right");
                    Wait(0.2);
                    drive(47, "forward");
                    Wait(0.2);
                    turn(62, "left");
                    Wait(0.2);
                    drive(42, "forward");
                    Wait(0.2);
                    turn(75,"right");
                    drive(3, "backward");
                    ServoFaceDown();
                    Wait(0.5);
                    claim.setPosition(0.75);
                    Wait(0.5);
                    drive(58, "backward", 1);
                    break;
                
           } telemetry.addData("stopRobot", time);
            telemetry.update();
            Nothing();
            stop();
            telemetry.addData("FinishTime", time);
            telemetry.update();
            //ready to start next to the crater
        }
    }
    
    public void MoveHook(double timeneeded, double power) {
        time = 0;
        resetStartTime();
        while (time<=timeneeded) {
            hook.setPower(power);
        }
        hook.setPower(0);
    }
    
    /*public void rotateArm(double timeneeded) {
        time = 0;
        resetStartTime();
        while (time<=timeneeded) {
            armRotator.setPower(1);
        }
        armRotator.setPower(0);
    }*/
        
        
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
            bLDC.setPower(-power);
            bRDC.setPower(power);
        }
        Nothing();
    }
    
    public void drive(double distance, String direction, double powerNeeded) {
        Nothing();
    
        // check the direction
    
        double power=0;
        if (direction=="forward") {
            power = powerNeeded;
        } else if (direction == "backward") {
            power = -powerNeeded;
        }
        // calculate time needed
        double speed = Constants.unispeed;
        double timeneeded = 0;
        timeneeded = distance/speed;
        time = 0;
        resetStartTime();
        // set power by direction
        while (time<=timeneeded) {
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
            bLDC.setPower(power);
            bRDC.setPower(power);
        }
        Nothing();
    }
    
    public void Wait(double amount) {
        time = 0;
        resetStartTime();
        while (time<=amount) {
            bLDC.setPower(0);
            bRDC.setPower(0);   
        }
    }
    
    public void Nothing() {
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
    
    //Mineral Sensing Methods
    
    private void initVuforia() {
        /*
         * Configure Vuforia by creating a Parameter object, and passing it to the Vuforia engine.
         */
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;

        //  Instantiate the Vuforia engine
        vuforia = ClassFactory.getInstance().createVuforia(parameters);


        // Loading trackables is not necessary for the Tensor Flow Object Detection engine.
    }

    /**
     * Initialize the Tensor Flow Object Detection engine.
     */
    private void initTfod() {
        int tfodMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
                "tfodMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
        tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABEL_GOLD_MINERAL, LABEL_SILVER_MINERAL);
    }

    public int findGoldPosition(double timetofind){
        int position=0;
        time=0;
        runtime.reset();

        while(runtime.time() < timetofind) { // I just put this number in as an example.
            // run tensorflow code

                if (tfod != null) {
                    // getUpdatedRecognitions() will return null if no new information is available since
                    // the last time that call was made.
                    List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
                    if (updatedRecognitions != null) {
                        telemetry.addData("# Object Detected", updatedRecognitions.size());
                        if (updatedRecognitions.size() == 2) {
                            int goldMineralX = -1;
                            int silverMineral1X = -1;
                            int silverMinera21X = -1;

                            for (Recognition recognition : updatedRecognitions) {

                                if (recognition.getLabel().equals(LABEL_GOLD_MINERAL)) {
                                    goldMineralX = (int) recognition.getLeft();
                                } else if (silverMineral1X == -1) {
                                    silverMineral1X = (int) recognition.getLeft();
                                } else {

                                    silverMinera21X = (int) recognition.getLeft();
                                }
                            }

                                    telemetry.addData("Gold position", goldMineralX);
                                    telemetry.addData("Silver position", silverMineral1X);

                            if (goldMineralX != -1 && silverMineral1X != -1) {
                                if (goldMineralX < silverMineral1X ) {
                                    telemetry.addData("Gold Mineral Position", "Left");
                                    position=1;
                                } else if (goldMineralX > silverMineral1X ) {
                                    telemetry.addData("Gold Mineral Position", "Center");
                                    position=2;
                                }
                            }
                            if(position !=1 && position != 2){

                                telemetry.addData("Gold Mineral Position", "Right");
                                position=3;
                            }
                        }
                        telemetry.update();
                    }
                }

        }

        telemetry.update();

        return position;
    }
    
    /**The OVERRIDE METHOD for sensing used to check the right side. 
     * Do not change.
     * */
     public int findGoldPosition(double timetofind,String cameraPosition){
        int position=0;
        time=0;
        runtime.reset();

        while(runtime.time() < timetofind) { // I just put this number in as an example.
            // run tensorflow code

            if (tfod != null) {
                // getUpdatedRecognitions() will return null if no new information is available since
                // the last time that call was made.
                List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
                if (updatedRecognitions != null) {
                    telemetry.addData("# Object Detected", updatedRecognitions.size());
                    if (updatedRecognitions.size() == 2) {
                        int goldMineralX = -1;
                        int silverMineral1X = -1;
                        int silverMinera21X = -1;

                        for (Recognition recognition : updatedRecognitions) {

                            if (recognition.getLabel().equals(LABEL_GOLD_MINERAL)) {
                                goldMineralX = (int) recognition.getLeft();
                            } else if (silverMineral1X == -1) {
                                silverMineral1X = (int) recognition.getLeft();
                            } else {

                                silverMinera21X = (int) recognition.getLeft();
                            }
                        }

                        telemetry.addData("Gold position", goldMineralX);
                        telemetry.addData("Silver position", silverMineral1X);

                     if(cameraPosition.equalsIgnoreCase("left")) {

                         if (goldMineralX != -1 && silverMineral1X != -1) {
                             if (goldMineralX < silverMineral1X) {
                                 telemetry.addData("Gold Mineral Position", "Left");
                                 position = 1;
                             } else if (goldMineralX > silverMineral1X) {
                                 telemetry.addData("Gold Mineral Position", "Center");
                                 position = 2;
                             }
                         }
                         if (position != 1 && position != 2) {

                             telemetry.addData("Gold Mineral Position", "Right");
                             position = 3;
                         }
                     }
                     //If the camera is turned to right view
                     else if(cameraPosition.equalsIgnoreCase(("Right"))) {

                         if (goldMineralX != -1 && silverMineral1X != -1) {
                             if (goldMineralX > silverMineral1X) {
                                 telemetry.addData("Gold Mineral Position", "Right");
                                 position = 1;
                             } else if (goldMineralX < silverMineral1X) {
                                 telemetry.addData("Gold Mineral Position", "Center");
                                 position = 2;
                             }
                         }
                         if (position != 1 && position != 2) {

                             telemetry.addData("Gold Mineral Position", "Left");
                             position = 3;
                         }









                     }
                    }
                    telemetry.update();
                }
            }

        }

        telemetry.update();

        return position;
    }

    
}

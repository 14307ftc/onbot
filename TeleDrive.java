package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;

import java.util.List;

@Autonomous(name = "Tensor: Camera", group = "ConceptCamera")


public class TeleDrive extends LinearOpMode {

    /* Declare OpMode members. */
    HardwareInitializer         robot   = new HardwareInitializer();   // Use a Pushbot's hardware
    private ElapsedTime runtime = new ElapsedTime();

    static final double     COUNTS_PER_MOTOR_REV    = 1440 ;    // eg: TETRIX Motor Encoder
    static final double     DRIVE_GEAR_REDUCTION    = 0.8 ;     // This is < 1.0 if geared UP
    static final double     WHEEL_DIAMETER_INCHES   = 5.0 ;     // For figuring circumference
    static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415);
    static final double     DRIVE_SPEED             = 0.6;
    static final double     TURN_SPEED              = 0.5;

    //For Mineral Sensing
    private static final String TFOD_MODEL_ASSET = "RoverRuckus.tflite";
    private static final String LABEL_GOLD_MINERAL = "Gold Mineral";
    private static final String LABEL_SILVER_MINERAL = "Silver Mineral";
    private static final String VUFORIA_KEY = "AWC+eTP/////AAABmVFM7Rg0bUFwvGmDUhcT2tOLaMdN9n9i28A/yKKpI2xh17wN0o9U+CrjY0WkP/nROG2bBfy9Ew/V06Z5d+ht93k0C9pa8wIKPNkSSfiIRmlkUo7FXf5hKM2W7RfLyoSlMA4jcqZL0gY5LrbQpnB07QVLO9hyd7XuVvBHxul8FCFU1fBHfuZTEHbg13F7wpCl6XtZXHHRyGbgJrnaM4RnYy8QXypI72RjHQo/jU+Lqz6qDkYl2kT21pvkMXGn1g/0efSj6Ichd5VYdr6Au6aP3OQayKFHxMgh3XzjKCgKvPYap3rIJWQk6jSL7cedkuxJ/SbBk6vNvVLvC30kntRKM53rD6UqFR6f7X/ABdDwX40W";
    private VuforiaLocalizer vuforia;
    private TFObjectDetector tfod;


    public void runOpMode() {

        /*
         * Initialize the drive system variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Resetting Encoders");    //
        telemetry.update();


      /*  robot.leftDriveFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.leftDriveBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.rightDriveFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.rightDriveBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        robot.leftDriveFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.leftDriveBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.rightDriveFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.rightDriveBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

*/

        initVuforia();

        if (ClassFactory.getInstance().canCreateTFObjectDetector()) {
            initTfod();
        } else {
            telemetry.addData("Sorry!", "This device is not compatible with TFOD");
        }

        /** Wait for the game to begin */
        telemetry.addData(">", "Press Play to start tracking");
        telemetry.update();
        // Wait for the game to start (driver presses PLAY)
        waitForStart();



        while (opModeIsActive()) {

            time = 0;
            resetStartTime();
            //Moving with time
            telemetry.addData("StartingTime", time);
            telemetry.addLine();

            //1. Move the hook
            // MoveHook(4.45);
            //Wait(1);
            //2. Adjust position

            //3. Mineral Sensing

            if (tfod != null) {
                tfod.activate();
            }

            int position=findGoldPosition(4.0);
            if (tfod != null) {
                tfod.shutdown();
            }

          /*  switch(position){
                //Gold Position left --1  Center --2 Right ---3
                case 1:
                    turn(30,"left");
                    Wait(1);
                    drive(40, "forward");
                    Wait(1);
                    turn(70,"right");
                    Wait(1);
                    drive(40, "forward");
                    Wait(1);
                    turn(90, "right");
                    Wait(1);
                    drive(120, "forward");

                    break;
                case 2:
                    telemetry.addData("Moving", "Center");
                    drive(60, "forward");
                    Wait(1);
                    turn(45,"right");
                    Wait(1);
                    turn(90,"right");
                    Wait(1);
                    drive(120, "forward");
                    break;

                case 3:
                    turn(40,"right");
                    Wait(1);
                    drive(50, "forward");
                    Wait(1);
                    turn(40, "left");
                    Wait(1);
                    drive(40, "forward");
                    Wait(1);
                    drive(120, "backward");
                    break;

            }*/

            //4. Move to mineral and push
            //5. Adjust Position
            //6. Move to drop the marker
            //7. Drop the marker
            //8. Move to park
            //9. Parking
            stop();
        }




        // Step through each leg of the path,
        // Note: Reverse movement is obtained by setting a negative distance (not speed)
        //encoderDrive(DRIVE_SPEED,  12,  12, 5.0);  // S1: Forward 47 Inches with 5 Sec timeout
        // encoderDrive(TURN_SPEED,   12, -12, 4.0);  // S2: Turn Right 12 Inches with 4 Sec timeout
        //encoderDrive(DRIVE_SPEED, -24, -24, 4.0);  // S3: Reverse 24 Inches with 4 Sec timeout


        telemetry.addData("Path", "Complete");
        telemetry.update();
    }


    /*
     *  Method to perfmorm a relative move, based on encoder counts.
     *  Encoders are not reset as the move is based on the current position.
     *  Move will stop if any of three conditions occur:
     *  1) Move gets to the desired position
     *  2) Move runs out of time
     *  3) Driver stops the opmode running.
     */
    public void encoderDrive(double speed,
                             double leftInches, double rightInches,
                             double timeoutS) {
        int newLeftTarget;
        int newRightTarget;

        // Ensure that the opmode is still active
        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            newLeftTarget = robot.leftDriveFront.getCurrentPosition() + (int)(leftInches * COUNTS_PER_INCH);
            newRightTarget = robot.rightDriveBack.getCurrentPosition() + (int)(rightInches * COUNTS_PER_INCH);
            robot.leftDriveFront.setTargetPosition(newLeftTarget);
            robot.rightDriveFront.setTargetPosition(newRightTarget);

            // Turn On RUN_TO_POSITION
            robot.leftDriveFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.rightDriveFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            runtime.reset();
            robot.leftDriveFront.setPower(-Math.abs(speed));
            robot.leftDriveBack.setPower(-Math.abs(speed));
            robot.rightDriveFront.setPower(Math.abs(speed));
            robot.rightDriveBack.setPower(Math.abs(speed));

            // keep looping while we are still active, and there is time left, and both motors are running.
            // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
            // its target position, the motion will stop.  This is "safer" in the event that the robot will
            // always end the motion as soon as possible.
            // However, if you require that BOTH motors have finished their moves before the robot continues
            // onto the next step, use (isBusy() || isBusy()) in the loop test.
            while (opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (robot.leftDriveFront.isBusy() && robot.leftDriveFront.isBusy())) {

                // Display it for the driver.
                telemetry.addData("Path1",  "Running to %7d :%7d", newLeftTarget,  newRightTarget);
                telemetry.addData("Path2",  "Running at %7d :%7d",
                        robot.leftDriveFront.getCurrentPosition(),
                        robot.rightDriveFront.getCurrentPosition());
                telemetry.update();
            }

            // Stop all motion;
            robot.leftDriveFront.setPower(0);
            robot.leftDriveBack.setPower(0);
            robot.rightDriveFront.setPower(0);
            robot.rightDriveBack.setPower(0);
            // Turn off RUN_TO_POSITION
            robot.leftDriveFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.rightDriveFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            //  sleep(250);   // optional pause after each move
        }
    }

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



    public void MoveHook(double timeneeded) {
        time = 0;
        resetStartTime();
        while (time<=timeneeded) {
          //  robot.rackPinion.setPower(1);
        }
//robot.rackPinion.setPower(0);
    }

    //Driving the robot (enter diastance to move forward in inches and direction forward or backward)
    public void drive(double distance, String direction) {
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
            robot.leftDriveFront.setPower(-power);
            robot.rightDriveFront.setPower(power);
            robot.leftDriveBack.setPower(-power);
            robot.rightDriveBack.setPower(power);
        }
        Nothing();
    }

    //Turning the robot (enter amount of degrees to turn and direction to turn(left or right))
    public void turn(double degrees, String direction) {
        // check the direction
        double power=0;
        if (direction=="left") {
            power = 0.4;
        } else if (direction == "right") {
            power = -0.4;
        }
        // calculate time needed
        double speed =  Constants.uniturn;
        double timeneeded = 0;
        timeneeded = degrees/speed;
        time = 0;
        resetStartTime();
        // set power by direction
        while (time<=timeneeded) {
            robot.leftDriveBack.setPower(power);
            robot.leftDriveFront.setPower(power);
            robot.rightDriveFront.setPower(power);
            robot.rightDriveBack.setPower(power);
        }
        Nothing();
    }

    //Wait for the amount of time entered
    public void Wait(double amount) {
        time = 0;
        resetStartTime();
        while (time<=amount) {
            robot.leftDriveBack.setPower(0);
            robot.leftDriveFront.setPower(0);
            robot.rightDriveFront.setPower(0);
            robot.rightDriveBack.setPower(0);
        }
    }

    //Do nothing
    public void Nothing() {
        robot.leftDriveBack.setPower(0);
        robot.leftDriveFront.setPower(0);
        robot.rightDriveFront.setPower(0);
        robot.rightDriveBack.setPower(0);
    }

    //Servo Methods
    public void ServoFaceUp(){
        //claim.setPosition(0);
    }
    public void ServoFaceDown(){
        //claim.setPosition(1);
    }








}

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;


public class UniversalMethods {
    private DcMotor fLDC;
    private DcMotor fRDC;
    private DcMotor bLDC;
    private DcMotor bRDC;
    private DcMotor hook;
    private Servo claim;
    
    
    
    public void Nothing() {
        fLDC.setPower(0);
        fRDC.setPower(0);
        bLDC.setPower(0);
        bRDC.setPower(0);
    }
}

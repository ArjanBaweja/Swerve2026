package Subsystems.Drive;

import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class SwerveModules {
    private DcMotorEx driveMotor;
    private CRServo steerServo;
    private AnalogInput aE;
    private double kP = 0.7; // tune
    private double kD = 0.05; // tune

    private double lastError = 0;

    private static final double absError = 0.02; //acceptable angle error

    public SwerveModules(DcMotorEx motor, CRServo servo, AnalogInput encoder, HardwareMap hm){
        driveMotor = motor;
        steerServo = servo;
        aE = encoder;

    }

    public void applyPower(double P){

        driveMotor.setPower(P);
    }


}

package org.firstinspires.ftc.teamcode.Subsystems.Drive;

import static androidx.core.math.MathUtils.clamp;

import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
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

    public SwerveModules(DcMotorEx motor, CRServo servo, AnalogInput encoder){
        driveMotor = motor;
        steerServo = servo;
        aE = encoder;
        driveMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        driveMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void applyPower(double P){
        driveMotor.setPower(P);
    }

    public double getCurrentAngle() {
        return Math.atan2(Math.sin((aE.getVoltage() / 3.3) * 2 * Math.PI), Math.cos((aE.getVoltage() / 3.3) * 2 * Math.PI)); //angle [pi,-pi]
    }

    public void Update(double targetAngle, double power) {
        double current = getCurrentAngle();
        double error = Math.atan2(Math.sin(targetAngle - current), Math.cos(targetAngle - current)); //error [pi,-pi]

        //optimization shuold be better in case of like at 10 target -100
        if (Math.abs(error) > Math.PI / 2) {
            targetAngle = targetAngle + Math.PI;
            power       = -power;
            error       = Math.atan2(Math.sin(targetAngle - current), Math.cos(targetAngle - current)); //error [pi,-pi]
        }

        if (Math.abs(error) < absError) {
            steerServo.setPower(0);
            driveMotor.setPower(power);
            lastError = 0;
            return;
        }

        // pppppppppppppppppppppppppppppppppppppppdddddddddddddddddddddddddddddddddddddddddddddddd
        double P = kP * error;
        double D = kD * (error - lastError);
        lastError = error;

        steerServo.setPower(clamp(P + D, -1.0, 1.0));
        driveMotor.setPower(power);
    }

    public void stop() {
        steerServo.setPower(0);
        driveMotor.setPower(0);
        lastError = 0;
    }


}

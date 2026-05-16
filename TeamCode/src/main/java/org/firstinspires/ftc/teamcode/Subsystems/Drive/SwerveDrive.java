package org.firstinspires.ftc.teamcode.Subsystems.Drive;

import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class SwerveDrive {

    private SwerveModules frontLeft, frontRight, backLeft, backRight;

    private double L = 7.0;
    private double W = 7.0;

    public SwerveDrive(HardwareMap hm) {
        frontLeft = new SwerveModules(
                hm.get(DcMotorEx.class,   "fl_drive"),
                hm.get(CRServo.class,     "fl_steer"),
                hm.get(AnalogInput.class, "fl_encoder")
        );
        frontRight = new SwerveModules(
                hm.get(DcMotorEx.class,   "fr_drive"),
                hm.get(CRServo.class,     "fr_steer"),
                hm.get(AnalogInput.class, "fr_encoder")
        );
        backLeft = new SwerveModules(
                hm.get(DcMotorEx.class,   "bl_drive"),
                hm.get(CRServo.class,     "bl_steer"),
                hm.get(AnalogInput.class, "bl_encoder")
        );
        backRight = new SwerveModules(
                hm.get(DcMotorEx.class,   "br_drive"),
                hm.get(CRServo.class,     "br_steer"),
                hm.get(AnalogInput.class, "br_encoder")
        );
    }

    public void drive(double x, double y, double turn) {

        double[] rx = {-W,  W, -W,  W}; // FL, FR, BL, BR
        double[] ry = { L,  L, -L, -L};

        double[] angles = new double[4];
        double[] speeds = new double[4];

        // vector for each
        for (int i = 0; i < 4; i++) {
            double vx = x + turn * (-ry[i]);
            double vy = y + turn * ( rx[i]);

            angles[i] = Math.atan2(vx, vy);
            speeds[i] = Math.hypot(vx, vy);
        }

        // insure vector<=1
        double max = 0;
        for (double s : speeds) max = Math.max(max, s);
        if (max > 1.0)
            for (int i = 0; i < 4; i++) speeds[i] /= max;

        // send to modules
        frontLeft.Update(angles[0], speeds[0]);
        frontRight.Update(angles[1], speeds[1]);
        backLeft.Update(angles[2], speeds[2]);
        backRight.Update(angles[3], speeds[3]);
    }

    public void stop() {
        frontLeft.stop();
        frontRight.stop();
        backLeft.stop();
        backRight.stop();
    }

}
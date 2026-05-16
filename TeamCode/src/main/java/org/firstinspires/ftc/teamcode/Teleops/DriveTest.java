package org.firstinspires.ftc.teamcode.Teleops;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.Subsystems.Drive.SwerveDrive;

@TeleOp(name = "FullTest")
public class DriveTest extends LinearOpMode {

    SwerveDrive drive;

    @Override
    public void runOpMode() {
        drive = new SwerveDrive(hardwareMap);

        waitForStart();
        while (opModeIsActive()) {
            Swerve();
        }
    }

    public void Swerve() {
        double x = gamepad1.left_stick_x;
        double y = -gamepad1.left_stick_y;
        double t = gamepad1.right_stick_x;
        drive.drive(x, y, t);
    }
}
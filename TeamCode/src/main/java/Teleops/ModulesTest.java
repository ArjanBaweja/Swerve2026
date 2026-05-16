package Teleops;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import Subsystems.Drive.SwerveModules;


@TeleOp(name="SingleModuleTest")
public class ModulesTest extends LinearOpMode {
    @Override
    public void runOpMode() {

        SwerveModules module = new SwerveModules(
                hardwareMap.get(DcMotorEx.class,  "fld"),
                hardwareMap.get(CRServo.class,    "fls"),
                hardwareMap.get(AnalogInput.class,"fle")
        );

        waitForStart();

        while (opModeIsActive()) {
            double x = gamepad1.left_stick_x;
            double y = -gamepad1.left_stick_y;

            double angle = Math.atan2(x, y);
            double power = Math.hypot(x, y);

            if (power > 0.05) {
                module.Update(angle, power);
            } else {
                module.stop();
            }

            telemetry.addData("target", Math.toDegrees(angle));
            telemetry.addData("postion", Math.toDegrees(module.getCurrentAngle()));
            telemetry.addData("power", power);
            telemetry.update();
        }
    }
}

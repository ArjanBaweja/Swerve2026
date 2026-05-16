package org.firstinspires.ftc.teamcode.Teleops;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.CRServo;

@TeleOp(name="OnlySevos")

public class ServoTests extends LinearOpMode {
    CRServo Sc1, Sc2, Se1, Se2;
    AnalogInput Ec1, Ec2, Ee1, Ee2;

    @Override
    public void runOpMode() {

        Sc1 = hardwareMap.get(CRServo.class, "Sc1");
        Sc2 = hardwareMap.get(CRServo.class, "Sc2");
        Se1 = hardwareMap.get(CRServo.class, "Se1");
        Se2 = hardwareMap.get(CRServo.class, "Se2");

        Ec1 = hardwareMap.get(AnalogInput.class, "Ec1");
        Ec2 = hardwareMap.get(AnalogInput.class, "Ec2");
        Ee1 = hardwareMap.get(AnalogInput.class, "Ee1");
        Ee2 = hardwareMap.get(AnalogInput.class, "Ee2");

        waitForStart();

        while (opModeIsActive()) {

            double power = -gamepad1.left_stick_y;

            Sc1.setPower(power);
            Sc2.setPower(power);
            Se1.setPower(power);
            Se2.setPower(power);

            double Sc1Deg = (Ec1.getVoltage() / 3.3) * 360;
            double Sc2Deg = (Ec2.getVoltage() / 3.3) * 360;
            double Se1Deg = (Ee1.getVoltage() / 3.3) * 360;
            double Se2Deg = (Ee2.getVoltage() / 3.3) * 360;

            telemetry.addData("P", power);
            telemetry.addData("Sc1 voltage", Ec1.getVoltage());
            telemetry.addData("Sc2 voltage", Ec2.getVoltage());
            telemetry.addData("Se1 voltage", Ee1.getVoltage());
            telemetry.addData("Se2 voltage", Ee2.getVoltage());
            telemetry.addData("Sc1 degrees", Sc1Deg);
            telemetry.addData("Sc2 degrees", Sc2Deg);
            telemetry.addData("Se1 degrees", Se1Deg);
            telemetry.addData("Se2 degrees", Se2Deg);
            telemetry.update();
        }

        Sc1.setPower(0);
        Sc2.setPower(0);
        Se1.setPower(0);
        Se2.setPower(0);
    }
}

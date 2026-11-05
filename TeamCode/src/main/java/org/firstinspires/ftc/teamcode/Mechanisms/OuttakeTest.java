package org.firstinspires.ftc.teamcode.Mechanisms;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@TeleOp
public class OuttakeTest extends LinearOpMode {

    private DcMotorEx outtake;
    public static double OuttakeSpeed = 3000;
    //reverse if wrong direction TPS
    @Override
    public void runOpMode() {
        // Hardware Mapping
        outtake = hardwareMap.get(DcMotorEx.class, "outtake");
        outtake.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        waitForStart();

        while (opModeIsActive()) {
            outtake.setVelocity(OuttakeSpeed);

            telemetry.addData("Outtake Power", outtake.getVelocity());
            telemetry.update();
        }
    }
}
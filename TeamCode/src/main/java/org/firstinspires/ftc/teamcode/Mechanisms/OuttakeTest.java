package org.firstinspires.ftc.teamcode.Mechanisms;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@TeleOp
public class OuttakeTest extends LinearOpMode {

    private DcMotorEx outtake;
    public static double OuttakeSpeed = 2700;
    //reverse if wrong direction TPS
    @Override
    public void runOpMode() {
        // Hardware Mapping
        outtake = hardwareMap.get(DcMotorEx.class, "outtake");
        telemetry.update();


        waitForStart();

        while (opModeIsActive()) {
            outtake.setVelocity(OuttakeSpeed);

            telemetry.addData("Outtake Power", outtake.getPower());
            telemetry.update();
        }
    }
}
package Mechanisms;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@TeleOp
public class OuttakeTest extends LinearOpMode {

    private DcMotorEx outtake;

    @Override
    public void runOpMode() {
        // Hardware Mapping
        outtake = hardwareMap.get(DcMotorEx.class, "outtake");
        telemetry.update();
        double OuttakeSpeed = 1;
        //reverse if wrong direction
        waitForStart();

        while (opModeIsActive()) {
            if (gamepad1.right_trigger > 0.1) {
                outtake.setPower(OuttakeSpeed);
            } else {
                outtake.setPower(0);
            }

            telemetry.addData("Outtake Power", outtake.getPower());
            telemetry.update();
        }
    }
}
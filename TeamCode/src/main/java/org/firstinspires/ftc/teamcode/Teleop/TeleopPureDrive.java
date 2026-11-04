package org.firstinspires.ftc.teamcode.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

@TeleOp
public class TeleopPureDrive extends LinearOpMode {
    DcMotorEx fL, bL, fR, bR;


    @Override
    public void runOpMode() throws InterruptedException {
        // Declare our motors as DcMotorEx

        // Fetch as DcMotorEx from hardwareMap
        fL = hardwareMap.get(DcMotorEx.class, "fL");
        bL = hardwareMap.get(DcMotorEx.class, "bL");
        fR = hardwareMap.get(DcMotorEx.class, "fR");
        bR = hardwareMap.get(DcMotorEx.class, "bR");

        fL.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        bL.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        fR.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        bR.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);


        fL.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        bL.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        fR.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        bR.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);

        // Reverse the right side motors
        fR.setDirection(DcMotorSimple.Direction.REVERSE);
        bR.setDirection(DcMotorSimple.Direction.REVERSE);


        //Change any incorrect values here based off testing

        // Retrieve the IMU from the hardware map
        IMU imu = hardwareMap.get(IMU.class, "imu");
        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.FORWARD));
        imu.initialize(parameters);

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {


            double y = -gamepad1.left_stick_y;
            double x = gamepad1.left_stick_x;
            double rx = gamepad1.right_stick_x;

            if (gamepad1.options) {
                imu.resetYaw();
            }

                double botHeading = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);

                // Fixed broken formatting and missing '-' operator in rotX calculation
                double rotX = x * Math.cos(-botHeading) - y * Math.sin(-botHeading);
                double rotY = x * Math.sin(-botHeading) + y * Math.cos(-botHeading);

                rotX = rotX * 1.1; // Counteract imperfect strafing

                // Calculate drive power ratios
                double denominator = Math.max(Math.abs(rotY) + Math.abs(rotX) + Math.abs(rx), 1);
                double fLPower = (rotY + rotX + rx) / denominator;
                double bLPower = (rotY - rotX + rx) / denominator;
                double fRPower = (rotY - rotX - rx) / denominator;
                double bRPower = (rotY + rotX - rx) / denominator;

                fL.setPower(fLPower);
                bL.setPower(bLPower);
                fR.setPower(fRPower);
                bR.setPower(bRPower);

        }
    }
}
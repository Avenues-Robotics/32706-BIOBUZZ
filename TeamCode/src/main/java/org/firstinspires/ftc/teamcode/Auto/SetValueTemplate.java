package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous(name = "Nitro Set Value Auto", group = "Autonomous")
public class SetValueTemplate extends LinearOpMode {

    // Drive motors
    DcMotorEx fl, fr, bl, br;

    // Subsystem motors
    DcMotorEx intake, transfer, outtake;

    // Tunable constants
    public static double TICKS_PER_CM = 100.0;
    public static double TICKS_PER_DEGREE = 15.8;
    public static double STRAFE_TICKS_PER_CM = 110.0;

    @Override
    public void runOpMode() {


        fl = hardwareMap.get(DcMotorEx.class, "fl");
        fr = hardwareMap.get(DcMotorEx.class, "fr");
        bl = hardwareMap.get(DcMotorEx.class, "bl");
        br = hardwareMap.get(DcMotorEx.class, "br");


        intake = hardwareMap.get(DcMotorEx.class, "intake");
        transfer = hardwareMap.get(DcMotorEx.class, "transfer");
        outtake = hardwareMap.get(DcMotorEx.class, "outtake");


        fl.setDirection(DcMotorSimple.Direction.FORWARD);
        bl.setDirection(DcMotorSimple.Direction.FORWARD);
        fr.setDirection(DcMotorSimple.Direction.REVERSE);
        br.setDirection(DcMotorSimple.Direction.REVERSE);


        transfer.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        intake.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        outtake.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        //TBD (Might Run With)

        fl.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        fr.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        bl.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        br.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);

        waitForStart();

        if (opModeIsActive()) {
            // Write Here: (Test
            drive(1.0, 100.0);   // 100 cm at 1.0 power
            strafe(0.8, 40.0);   // 40 cm at 0.8 power
            turn(0.8, 180.0);    // 180 degrees at 0.8 power



        }
    }
    /*-----------------------------------------------------------------------
 --------------------------------Drive State---------------------------------
 ----------------------------------------------------------------------------
  */
    public void drive(double speed, double distance) {
        int ticks = (int)(distance * TICKS_PER_CM);

        if (opModeIsActive()) {
            fl.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
            fr.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
            bl.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
            br.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

            fl.setTargetPosition(ticks);
            fr.setTargetPosition(ticks);
            bl.setTargetPosition(ticks);
            br.setTargetPosition(ticks);
            //if inverted, change here
            fl.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
            fr.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
            bl.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
            br.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);

            fl.setPower(speed);
            fr.setPower(speed);
            bl.setPower(speed);
            br.setPower(speed);

            while (opModeIsActive() && (fl.isBusy() || fr.isBusy() || bl.isBusy() || br.isBusy())) {
                telemetry.addData("Drive Distance (cm)", distance);
                telemetry.addData("Target Ticks", ticks);
                telemetry.update();
            }

            fl.setPower(0);
            fr.setPower(0);
            bl.setPower(0);
            br.setPower(0);
        }
    }

    /*-------------------------------------------------------------------------
    --------------------------------Turn State---------------------------------
    ---------------------------------------------------------------------------
     */
    public void turn(double speed, double degrees) {
        int ticks = (int)(degrees * TICKS_PER_DEGREE);

        if (opModeIsActive()) {
            fl.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
            fr.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
            bl.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
            br.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

            fl.setTargetPosition(ticks);
            fr.setTargetPosition(-ticks);
            bl.setTargetPosition(ticks);
            br.setTargetPosition(-ticks);
            //If inverted, change here.
            fl.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
            fr.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
            bl.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
            br.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);

            fl.setPower(speed);
            fr.setPower(speed);
            bl.setPower(speed);
            br.setPower(speed);

            while (opModeIsActive() && (fl.isBusy() || fr.isBusy() || bl.isBusy() || br.isBusy())) {
                telemetry.addData("Turn Degrees", degrees);
                telemetry.addData("Target Ticks", ticks);
                telemetry.update();
            }

            fl.setPower(0);
            fr.setPower(0);
            bl.setPower(0);
            br.setPower(0);
        }
    }

    /*-------------------------------------------------------------------------
 --------------------------------Strafe State----------------------------------
 ------------------------------------------------------------------------------
  */
    public void strafe(double speed, double distance) {
                //format for writing
        int ticks = (int)(distance * STRAFE_TICKS_PER_CM);

        if (opModeIsActive()) {
            fl.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
            fr.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
            bl.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
            br.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

            fl.setTargetPosition(ticks);
            fr.setTargetPosition(-ticks);
            bl.setTargetPosition(-ticks);
            br.setTargetPosition(ticks);
            //If inverted, change here.
            fl.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
            fr.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
            bl.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
            br.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);

            fl.setPower(speed);
            fr.setPower(speed);
            bl.setPower(speed);
            br.setPower(speed);

            while (opModeIsActive() && (fl.isBusy() || fr.isBusy() || bl.isBusy() || br.isBusy())) {
                telemetry.addData("Strafe Distance (cm)", distance);
                telemetry.addData("Target Ticks", ticks);
                telemetry.update();
            }

            fl.setPower(0);
            fr.setPower(0);
            bl.setPower(0);
            br.setPower(0);
        }
    }
}
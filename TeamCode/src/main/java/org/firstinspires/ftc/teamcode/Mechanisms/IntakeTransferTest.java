package org.firstinspires.ftc.teamcode.Mechanisms;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
//echo 'export PATH=$PATH:$HOME/Library/Android/sdk/platform-tools' >> ~/.zshrc
//adb connect 192.168.43.1:5555
//adb version
@TeleOp
public class IntakeTransferTest extends LinearOpMode {

    private DcMotorEx intake, transfer;

    //reverse if wrong direction TPS
    @Override
    public void runOpMode() {
        // Hardware Mapping
        intake = hardwareMap.get(DcMotorEx.class, "intake");
        transfer = hardwareMap.get(DcMotorEx.class,"transfer");
        intake.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        transfer.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);



        waitForStart();

        while (opModeIsActive()) {
              if (gamepad1.right_trigger > 0.1) {
                intake.setPower(1);
            } else if (gamepad1.left_trigger > 0.1) {
                intake.setPower(-1);
            } else{
                intake.setPower(0);
            }
              if (gamepad2.right_trigger > 0.1){
                  transfer.setPower(-1);
              }
              else if (gamepad2.left_trigger > 0.1){
                  transfer.setPower(1);
              }else{
                  transfer.setPower(0);
              }
        }
    }
}
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.teamcode.Capture;
import org.firstinspires.ftc.teamcode.DriveTrain;
import org.firstinspires.ftc.teamcode.DroneLauncher;
import org.firstinspires.ftc.teamcode.StackBreaker;
import org.firstinspires.ftc.teamcode.Suspension;
import org.firstinspires.ftc.teamcode.PixelDoor;

@TeleOp(name = "TeleOpAlexander")
public class TeleOpAlexander extends LinearOpMode {
    private DriveTrain dt;
    private PixelDoor pd;
    private StackBreaker sb;
    private Suspension sp;
    private DroneLauncher dr;
    private Capture cp;

    @Override
    public void runOpMode() {
        dt = new DriveTrain(this);
        pd = new PixelDoor(this);
        sb = new StackBreaker(this);
        sp = new Suspension(this);
        dr = new DroneLauncher(this);
        cp = new Capture(this);
        boolean isClosed = true;
        boolean isBPressed = false;
        boolean isYPressed = gamepad1.y;
        waitForStart();
        while (opModeIsActive()) {
            dt.setPower(-gamepad1.left_stick_y, (gamepad1.left_trigger - gamepad1.right_trigger) * 0.4, -gamepad1.left_stick_x * 0.6);

            if (gamepad1.x) {
                dt.switchSlowMode();
            }
            if (gamepad1.a) {
                dt.switchReverse();
            }

            if (gamepad2.b && isBPressed) {
                if (isClosed){
                    pd.openDoor();
                    isClosed = false;
                } else {
                    pd.closeDoor();
                    isClosed = true;
                }

            }
            if (gamepad1.y && !isYPressed){
                sb.switchPosition();
            }
            if (gamepad2.dpad_up){
                sp.moveUp();
            } else if (gamepad2.dpad_down){
                sp.moveDown();
            } else {
                sp.stop();
            }
            if (gamepad2.y){
                dr.releaseDrone();
            }
            if (gamepad2.left_bumper){
                cp.grabPixels();
            } else if (gamepad2.right_bumper){
                cp.throwPixels();
            } else {
                cp.stop();
            }
            isYPressed = gamepad1.y;
            isBPressed = gamepad2.b;
        }

    }
}

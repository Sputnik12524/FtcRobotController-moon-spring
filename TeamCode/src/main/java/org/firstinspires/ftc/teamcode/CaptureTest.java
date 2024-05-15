package org.firstinspires.ftc.teamcode.OOP;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.checkerframework.checker.units.qual.C;


@TeleOp (name="CaptureTest", group="Robot")

public class CaptureTest extends LinearOpMode {

    // initialising motors
    public Capture capture;

    @Override
    public void runOpMode() {
        capture = new Capture(this);
        waitForStart();
        while (opModeIsActive()) {
            if (gamepad2.left_bumper) {
                capture.grabPixels();
            } else if (gamepad2.right_bumper) {
                capture.throwPixels();
            } else {
                capture.stop();
            }

            sleep(50);
        }
    }
}

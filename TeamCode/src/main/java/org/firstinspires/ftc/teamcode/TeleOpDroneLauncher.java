package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


@TeleOp (name="DroneLauncherTest", group="Robot")

public class TeleOpDroneLauncher extends LinearOpMode {

    // initialising motors
    public DroneLauncher droneLauncher;

    @Override
    public void runOpMode() {
        droneLauncher = new DroneLauncher(this);
        waitForStart();
        while (opModeIsActive()) {
            if (gamepad2.y) {
                droneLauncher.releaseDrone();
            }

            sleep(50);
        }
    }
}

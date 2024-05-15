package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.util.ElapsedTime;

@Config
public class DroneLauncher {
    private LinearOpMode aggregate;
    private ElapsedTime timer = new ElapsedTime();
    private CRServo droneLauncher;

    public static double RELEASE_SPEED = 1;

    public DroneLauncher(LinearOpMode aggregate) {
        droneLauncher = aggregate.hardwareMap.get(CRServo.class, "droneLaunch");
        this.aggregate = this.aggregate;
    }

    public void releaseDrone() {
        timer.reset();
        droneLauncher.setPower(RELEASE_SPEED);
        while (timer.seconds() < 1) {
            droneLauncher.setPower(RELEASE_SPEED);
        }
        droneLauncher.setPower(0);
    }

}

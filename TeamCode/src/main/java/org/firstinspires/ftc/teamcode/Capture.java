package org.firstinspires.ftc.teamcode.OOP;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.util.ElapsedTime;

@Config
public class Capture {
    private DcMotor capture1;
    private LinearOpMode aggregate;

    public static double GRAB_SPEED = .8;
    public static double THROW_SPEED = -.2;


    public Capture(LinearOpMode aggregate) {
        capture1 = aggregate.hardwareMap.get(DcMotor.class, "capture1");
        this.aggregate = this.aggregate;
    }

    public void grabPixels() {
        capture1.setPower(-GRAB_SPEED);
    }

    public void throwPixels() {
        capture1.setPower(-THROW_SPEED);
    }

    public void stop() {
        capture1.setPower(0);
    }
}

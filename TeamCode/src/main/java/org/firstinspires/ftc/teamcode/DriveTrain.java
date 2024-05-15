package org.firstinspires.ftc.teamcode.OOP;


import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;

public class DriveTrain {
    private DcMotor leftFront;
    private DcMotor rightFront;
    private DcMotor leftBack;
    private DcMotor rightBack;
    private IMU imu;

    static final double PI = Math.PI;
    static final double WHEEL_DIAMETER = 10.1; // wheel diameter given in centimeters
    static final double PULSES = 537.7; //GoBILDA Yellow Jacket 19.2:1

    double distance(double centimeter) {
        return PULSES / (PI*WHEEL_DIAMETER) * centimeter;
    }
    private double multiplier = 1;
    private LinearOpMode aggregate;

    public DriveTrain(LinearOpMode aggregate) {
        leftFront = aggregate.hardwareMap.get(DcMotor.class, "left_front");
        leftBack = aggregate.hardwareMap.get(DcMotor.class, "left_back");
        rightBack = aggregate.hardwareMap.get(DcMotor.class, "right_back");
        rightFront = aggregate.hardwareMap.get(DcMotor.class, "right_front");

        leftFront.setDirection(DcMotor.Direction.FORWARD);
        rightFront.setDirection(DcMotor.Direction.REVERSE);
        leftBack.setDirection(DcMotor.Direction.FORWARD);
        rightBack.setDirection(DcMotor.Direction.REVERSE);

        RevHubOrientationOnRobot.LogoFacingDirection logoFacingDirection = RevHubOrientationOnRobot.LogoFacingDirection.RIGHT;
        RevHubOrientationOnRobot.UsbFacingDirection usbFacingDirection = RevHubOrientationOnRobot.UsbFacingDirection.UP;
        RevHubOrientationOnRobot orientationOnRobot = new RevHubOrientationOnRobot(logoFacingDirection, usbFacingDirection);

        imu = aggregate.hardwareMap.get(IMU.class, "imu");
        imu.initialize(new IMU.Parameters(orientationOnRobot));
        imu.resetYaw();

        this.aggregate = aggregate;
    }

    public void driveStraight(double DRIVE_SPEED, double Distance){
        leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        leftFront.setPower(DRIVE_SPEED);
        rightFront.setPower(DRIVE_SPEED);
        leftBack.setPower(DRIVE_SPEED);
        rightBack.setPower(DRIVE_SPEED);

        while(aggregate.opModeIsActive() && Math.abs(leftFront.getCurrentPosition()) < distance(Distance));

        leftFront.setPower(0);
        rightFront.setPower(0);
        leftBack.setPower(0);
        rightBack.setPower(0);

        aggregate.sleep(500);
    }

    public void turn(double TURNspeed,double degrees){
        leftFront.setPower(TURNspeed);
        rightFront.setPower(-TURNspeed);
        leftBack.setPower(TURNspeed);
        rightBack.setPower(-TURNspeed);
        imu.resetYaw();
        while (aggregate.opModeIsActive() && Math.abs(getHeading()) < degrees);
        leftFront.setPower(0);
        rightFront.setPower(0);
        leftBack.setPower(0);
        rightBack.setPower(0);
        aggregate.sleep(500);
    }

    public void setPower (double main, double side, double rotation) {
        leftFront.setPower(multiplier * (main + side + rotation));
        leftBack.setPower(multiplier * (main - side + rotation));
        rightFront.setPower(multiplier * (main - side - rotation));
        rightBack.setPower(multiplier * (main + side - rotation));
    }

    public void switchSlowMode() {
        if (Math.abs(multiplier) > 0.5) {
            multiplier /= 2;
        } else {
            multiplier *= 2;
        }
    }

    public void switchReverse() {
        multiplier = -multiplier;
    }

    public void side(double sideSPEED, double Distance1){
        leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        leftFront.setPower(-sideSPEED);
        rightFront.setPower(sideSPEED);
        leftBack.setPower(sideSPEED);
        rightBack.setPower(-sideSPEED);

        while(aggregate.opModeIsActive() && Math.abs(leftFront.getCurrentPosition()) < distance(Distance1));

        leftFront.setPower(0);
        rightFront.setPower(0);
        leftBack.setPower(0);
        rightBack.setPower(0);
        aggregate.sleep(500);
    }

    public double getHeading()
    {
        YawPitchRollAngles orientation = imu.getRobotYawPitchRollAngles();
        return orientation.getYaw(AngleUnit.DEGREES);
    }
}

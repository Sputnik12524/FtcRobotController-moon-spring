package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
@Config
public class PixelDoor {
    private Servo close;
    public static double OPEN_POSITION = 0.2;
    public static double CLOSE_POSITION = 0.45;
    public PixelDoor(LinearOpMode aggregate) {
        close = aggregate.hardwareMap.get(Servo.class, "close");
    }
    public void openDoor(){
        close.setPosition(OPEN_POSITION);
    }
    public void closeDoor(){
        close.setPosition(CLOSE_POSITION);
    }
}

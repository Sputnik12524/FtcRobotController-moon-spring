package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.OOP.PixelDoor;

@TeleOp(name = "PixelDoor")
public class TeleOpPixelDoor extends LinearOpMode {
    private PixelDoor pd;
    @Override
    public void runOpMode() {
        pd = new PixelDoor(this);
        waitForStart();
        while (opModeIsActive()) {
            if (gamepad1.x){
                pd.openDoor();
            }
            if (gamepad1.a){
                pd.closeDoor();
            }
        }
    }










}

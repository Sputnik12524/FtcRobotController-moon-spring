package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Backdrop;
import org.firstinspires.ftc.teamcode.Capture;
import org.firstinspires.ftc.teamcode.DriveTrain;
import org.firstinspires.ftc.teamcode.DroneLauncher;
import org.firstinspires.ftc.teamcode.PixelDoor;
import org.firstinspires.ftc.teamcode.StackBreaker;
import org.firstinspires.ftc.teamcode.Suspension;

@TeleOp(name = "TeleOpModules", group = "Robot")
public class TeleOpModules extends LinearOpMode {

    private DriveTrain dt;
    private Suspension sus;
    private StackBreaker sb;
    private PixelDoor pd;
    private DroneLauncher dl;
    private Capture cap;

    private boolean stateX = false;
    private boolean stateLeftBumper = false;
    private boolean stateRightBumper = false;



    @Override
    public void runOpMode() {
        dt = new DriveTrain(this);
        sus = new Suspension(this);
        sb = new StackBreaker(this);
        pd = new PixelDoor(this);
        dl = new DroneLauncher(this);
        cap = new Capture(this);

        waitForStart();
        while (opModeIsActive()) {

            if (gamepad2.x && !stateX) { // управление StackBreaker
                sb.switchPosition();
            }


            if (gamepad2.dpad_up) { // управление Suspension
                sus.moveUp();

            } else if (gamepad2.dpad_down) {
                sus.moveDown();

            } else {
                sus.stop();

            }

            if (gamepad2.b) { // управление PixelDoor
                pd.openDoor();
            }
            if (gamepad2.a) {
                pd.closeDoor();
            }

            if (gamepad2.left_bumper) { // управление Capture
                cap.grabPixels();
            } else if (gamepad2.right_bumper) {
                cap.throwPixels();
            } else {
                cap.stop();
            }

            if (gamepad2.y) { // управление DroneLauncher
                dl.releaseDrone();
            }

            // управление кб
            double main = gamepad1.left_stick_y;
            double side = -gamepad1.left_stick_x;
            double rotate = (gamepad1.left_trigger - gamepad1.right_trigger);
            dt.setPower(main, side, rotate);

            if(gamepad1.left_bumper && !stateLeftBumper) {
                dt.switchReverse();
            }
            if (gamepad1.right_bumper && !stateRightBumper) {
                dt.switchSlowMode();
            }
            stateLeftBumper = gamepad1.left_bumper;
            stateX = gamepad2.x;

            telemetry.addData("main", main);
            telemetry.addData("side", side);
            telemetry.addData("rotate", rotate);
            telemetry.update();



        }
    }
}
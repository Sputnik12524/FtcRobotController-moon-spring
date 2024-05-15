package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "AutoRed2", group = "Robot")

public class AutoRed2 extends LinearOpMode {
    //В этом разделе мы объявляем, какие объекты будем использовать
    private DriveTrain dt;
    private PixelDoor pd;
    private Backdrop bd;

    private double SPEED = 0.3;
    private double TURN_SPEED = 0.25;
    private double SIDE_SPEED = 0.2;

    @Override
    public void runOpMode() {
        //в этом разделе мы создаем и настраиваем объекты, которые будем использовать
        dt = new DriveTrain(this);
        pd = new PixelDoor(this);
        bd = new Backdrop(this);

        waitForStart();
        //в этом разделе мы пишем основной код, который работает после запуска программы
        dt.driveStraight(SPEED, 67.5);
        pd.openDoor();
        sleep(500);
        dt.driveStraight(-SPEED, 10);
        pd.closeDoor();
        sleep(500);
        dt.turn(TURN_SPEED, 82);
        dt.driveStraight(-SPEED, 100);
        bd.moveUp();
        sleep(1000);
        bd.release();
        sleep(1408);
        bd.moveDown();
        dt.driveStraight(SPEED, 10);
        dt.side(SIDE_SPEED, 70);

    }
}

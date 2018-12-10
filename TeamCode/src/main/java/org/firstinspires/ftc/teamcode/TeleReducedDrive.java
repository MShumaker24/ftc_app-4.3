package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="TeleReducedDrive", group="Iterative Opmode")
public class TeleReducedDrive extends OpMode
{
    // these are our motors
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor BleftDrive = null;
    private DcMotor BrightDrive = null;
    private DcMotor FleftDrive = null;
    private DcMotor FrightDrive = null;
    private DcMotor InOutM = null;
    private DcMotor UpDownML = null;
    private DcMotor UpDownMR = null;
    private DcMotor BrushM= null;
    public Servo MarkerS = null;
    public Servo cageServo = null;



    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");
        // these are what the motors are called on our hardware map
        BleftDrive  = hardwareMap.get(DcMotor.class, "LRDrive");
        BrightDrive = hardwareMap.get(DcMotor.class, "RRDrive");
        FleftDrive  = hardwareMap.get(DcMotor.class, "LFDrive");
        FrightDrive = hardwareMap.get(DcMotor.class, "RFDrive");
        UpDownML = hardwareMap.get(DcMotor.class, "UpDownML");
        UpDownMR = hardwareMap.get(DcMotor.class, "UpDownMR");
        InOutM = hardwareMap.get(DcMotor.class, "InOutM");
        BrushM = hardwareMap.get(DcMotor.class, "BrushM");
        MarkerS = hardwareMap.get(Servo.class, "MarkerS");
        cageServo = hardwareMap.get(Servo.class, "dump");

        //this sets direction for the motors
        BleftDrive.setDirection(DcMotor.Direction.REVERSE);
        BrightDrive.setDirection(DcMotor.Direction.FORWARD);
        FleftDrive.setDirection(DcMotor.Direction.FORWARD);
        FrightDrive.setDirection(DcMotor.Direction.REVERSE);
        UpDownML.setDirection(DcMotor.Direction.FORWARD);
        UpDownMR.setDirection(DcMotor.Direction.FORWARD);
        InOutM.setDirection(DcMotor.Direction.REVERSE);
        BrushM.setDirection(DcMotor.Direction.FORWARD);
        MarkerS.setDirection(Servo.Direction.FORWARD);


        telemetry.addData("Status", "Initialized");
    }

    @Override
    public void init_loop() {
    }

    @Override
    public void start() {
        runtime.reset();
    }

    @Override
    public void loop() {
        //this is tbe variables for our power
        double BleftPower;
        double BrightPower;
        double FleftPower;
        double FrightPower;
        double DownUpPower;
        double InOutPower;
        double BrushPower;
        double MarkerPower;

        // this sets how the motors will be controlled on our gamepad
        double drive = -gamepad1.left_stick_y;
        double turn  =  gamepad1.left_stick_x;
        double strafe = gamepad1.right_stick_x;
        double lift = gamepad2.left_stick_y;
        double out = gamepad2.right_stick_y;
        double brushF = -gamepad2.right_trigger;
        double brushB = gamepad2.left_trigger;
        boolean markerU = gamepad2.dpad_left;
        boolean markerD = gamepad2.dpad_right;
        //math to set values between 1 and -1
        BleftPower    = Range.clip(drive + strafe + turn, -0.75, 0.75) ;
        BrightPower   = Range.clip(drive - strafe - turn, -0.75, 0.75) ;
        FleftPower    = Range.clip(drive - strafe + turn, -0.75, 0.75) ;
        FrightPower   = Range.clip(drive + strafe - turn, -0.75, 0.75) ;
        BrushPower = Range.clip(brushF + brushB, -1.0, 1.0);
        DownUpPower = lift * 0.5;
        InOutPower = out * 1;

        //sets position to servos/direction to motors
        if (gamepad2.dpad_left) {
            MarkerS.setPosition(.3);
        }
        else if (gamepad2.dpad_right) {
            MarkerS.setPosition(.7);
        }

        if (gamepad2.x) {
            cageServo.setPosition(0.5);
        }
        else if (gamepad2.b) {
            cageServo.setPosition(0.2);
        }

        BleftDrive.setPower(BleftPower);
        BrightDrive.setPower(BrightPower);
        FleftDrive.setPower(FleftPower);
        FrightDrive.setPower(FrightPower);
        UpDownML.setPower(DownUpPower);
        UpDownMR.setPower(DownUpPower);
        InOutM.setPower(InOutPower);
        BrushM.setPower(BrushPower);


        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.addData("Motors", "Bleft (%.2f), Bright (%.2f), Fleft (%.2f), Fright (%.2f)", BleftPower, BrightPower,FleftPower, FrightPower);
    }

    @Override
    public void stop() {
    }

}
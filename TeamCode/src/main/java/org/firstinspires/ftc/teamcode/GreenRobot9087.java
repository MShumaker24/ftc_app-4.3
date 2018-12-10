
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class GreenRobot9087
{
    public DcMotor BleftDrive = null;
    public DcMotor BrightDrive = null;
    public DcMotor FleftDrive = null;
    public DcMotor FrightDrive = null;
    public DcMotor UpDownML = null;
    public DcMotor UpDownMR= null;
    public DcMotor InOutM = null;
    public Servo MarkerS = null;
    public Servo cageServo = null;

    public static final double MID_SERVO       =  0.5 ;
    public static final double ARM_UP_POWER    =  0.45 ;
    public static final double ARM_DOWN_POWER  = -0.45 ;

    HardwareMap hwMap           =  null;
    private ElapsedTime period  = new ElapsedTime();

    public GreenRobot9087(){
    }

    public void Mec(double x, double y, double rotation) {
        double BleftPower = x+y+rotation;
        double BrightPower = y-x-rotation;
        double FleftPower = y-x+rotation;
        double FrightPower = y+x-rotation;

        double maxPower = Math.max(FleftPower, Math.max(FrightPower, Math.max(BleftPower, BrightPower)));

        if (maxPower > 1) {
             BleftPower = BleftPower / maxPower;
             BrightPower = BrightPower / maxPower;
             FleftPower = FleftPower / maxPower;
             FrightPower = FrightPower / maxPower;
        }

        BleftDrive.setPower(BleftPower);
        BrightDrive.setPower(BrightPower);
        FleftDrive.setPower(BleftPower);
        FrightDrive.setPower(BrightPower);
    }



    public void init(HardwareMap ahwMap) {

        hwMap = ahwMap;

        BleftDrive = hwMap.get(DcMotor.class, "LRDrive");
        BrightDrive = hwMap.get(DcMotor.class, "RRDrive");
        FleftDrive = hwMap.get(DcMotor.class, "LFDrive");
        FrightDrive = hwMap.get(DcMotor.class, "RFDrive");
        UpDownML = hwMap.get(DcMotor.class, "UpDownML");
        UpDownMR = hwMap.get(DcMotor.class, "UpDownMR");
        InOutM = hwMap.get(DcMotor.class, "InOutM");
        MarkerS = hwMap.get(Servo.class, "MarkerS");

        BleftDrive.setDirection(DcMotor.Direction.REVERSE);
        BrightDrive.setDirection(DcMotor.Direction.FORWARD);
        FleftDrive.setDirection(DcMotor.Direction.FORWARD);
        FrightDrive.setDirection(DcMotor.Direction.REVERSE);
        UpDownML.setDirection(DcMotor.Direction.FORWARD);
        UpDownMR.setDirection(DcMotor.Direction.REVERSE);
        InOutM.setDirection(DcMotor.Direction.FORWARD);
        MarkerS.setDirection(Servo.Direction.FORWARD);

        BleftDrive.setPower(0);
        BrightDrive.setPower(0);
        FrightDrive.setPower(0);
        FleftDrive.setPower(0);
        UpDownML.setPower(0);
        UpDownMR.setPower(0);
        InOutM.setPower(0);

        BleftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BrightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FleftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FrightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        UpDownML.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        UpDownMR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        InOutM.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        BleftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        BrightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        FleftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        FrightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        UpDownML.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        UpDownMR  .setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        InOutM.setMode(DcMotor.RunMode.RUN_USING_ENCODER);



    }

}
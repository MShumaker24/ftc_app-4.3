/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;


@Autonomous(name="AutoMarker", group="Pushbot")
public class AutoMarker extends LinearOpMode {

    GreenRobot9087        robot   = new GreenRobot9087();   // Use a Pushbot's hardware
    private ElapsedTime     runtime = new ElapsedTime();

    //sets how fast our motors are going
    static final double     FORWARD_SPEED = 0.6;
    static final double     TURN_SPEED    = 1;
    static final double     UPDOWN_SPEED    = 1;
    static final double     INOUT_SPEED    = 1;


    @Override
    public void runOpMode() {

        robot.init(hardwareMap);

        telemetry.addData("Status", "Ready to run");
        telemetry.update();

        // -----------------------------------------------------------------------------------------
        //driving with Mec function: x= turn y = forward/backward rotation = strafe

        waitForStart();
        runtime.reset();
        robot.UpDownML.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.UpDownMR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.UpDownML.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.UpDownMR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.InOutM.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        //moves the robots base down
        robot.UpDownML.setPower(-UPDOWN_SPEED);
        robot.UpDownMR.setPower(UPDOWN_SPEED);
        while (opModeIsActive() && (runtime.seconds() < 1.1)) {
            telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
            telemetry.addData("UpDownML", robot.UpDownML.getCurrentPosition());
            telemetry.addData("UpDownMR", robot.UpDownMR.getCurrentPosition());
            telemetry.update();
        }

        //stops lowering the arm
        robot.UpDownML.setPower(0);
        robot.UpDownMR.setPower(0);

        // -----------------------------------------------------------------------------------------

        //moves the robots arm down, with the base
        waitForStart();
        runtime.reset();

        robot.InOutM.setPower(INOUT_SPEED);

        while (opModeIsActive() && (robot.InOutM.getCurrentPosition() < 11000)) {
            telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
            telemetry.addData("InOutM", robot.InOutM.getCurrentPosition());
            telemetry.update();
        }
        robot.InOutM.setPower(0);

        // -----------------------------------------------------------------------------------------

        //moves left
        runtime.reset();
        robot.Mec(0, 0, 0.65);
        while (opModeIsActive() && (runtime.seconds() < 1.0)) {
            telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }

        //stops
        robot.BleftDrive.setPower(0);
        robot.BrightDrive.setPower(0);
        robot.FleftDrive.setPower(0);
        robot.FrightDrive.setPower(0);

         // -----------------------------------------------------------------------------------------
        //moves forwards
        runtime.reset();
        robot.BleftDrive.setPower(.4);
        robot.BrightDrive.setPower(.6);
        robot.FleftDrive.setPower(.4);
        robot.FrightDrive.setPower(.6);
        while (opModeIsActive() && (runtime.seconds() < 1.8)) {
            telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }

        //stop
        robot.BleftDrive.setPower(0);
        robot.BrightDrive.setPower(0);
        robot.FleftDrive.setPower(0);
        robot.FrightDrive.setPower(0);

        // ------------------------------------------------------------------------------------------------

        /*
        //partial turn left
        runtime.reset();
        //robot.Mec(0, 0, 0.75);
        robot.BleftDrive.setPower(.75);
        robot.BrightDrive.setPower(-.75);
        robot.FleftDrive.setPower(-.75);
        robot.FrightDrive.setPower(.75);
        while (opModeIsActive() && runtime.seconds() < 0.75) {
            telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }
        */

        //stops
        runtime.reset();
        robot.Mec(0, 0, 0);
        while (opModeIsActive() && runtime.seconds() < 0.25) {

        }

        //moves marker into depot
        runtime.reset();
        robot.MarkerS.setPosition(0.3);
        while (opModeIsActive() && runtime.seconds() < 1.5) {

        }
        robot.MarkerS.setPosition(0.7);
        while (opModeIsActive() && runtime.seconds() < 1.5) {

        }

        //finish the turn
        runtime.reset();
        robot.BleftDrive.setPower(.75);
        robot.BrightDrive.setPower(-.75);
        robot.FleftDrive.setPower(-.75);
        robot.FrightDrive.setPower(.75);
        while (opModeIsActive() && runtime.seconds() < .9) {

        }

        //stops
        runtime.reset();
        robot.Mec(0, 0, 0);
        while (opModeIsActive() && runtime.seconds() < 0.25) {

        }

        //drive to opposing crater
        runtime.reset();
        robot.BleftDrive.setPower(.4);
        robot.BrightDrive.setPower(.6);
        robot.FleftDrive.setPower(.4);
        robot.FrightDrive.setPower(.6);
        //robot.Mec(0, .5, 0);
        while (opModeIsActive() && runtime.seconds() < 2.7) {

        }

        //stops
        runtime.reset();
        robot.Mec(0, 0, 0);

        // -------------------------------------------------------------------------------------------------
        telemetry.addData("Path", "Complete");
        telemetry.update();
        sleep(1000);

    }
}
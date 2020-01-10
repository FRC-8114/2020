package frc.robot.Auto;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.Timer;

public class AutoSegment {
    private DifferentialDrive motorDrive;

    //---------------------------------------------------------------\\
    //--Constructor--\\
    public AutoSegment(DifferentialDrive motorDrive) {
        this.motorDrive = motorDrive;

    }

    //---------------------------------------------------------------\\
    //--Scores points via shooting balls into the power port--\\
    public void shootPowerCells() {
        
    }

    //---------------------------------------------------------------\\
    //--Moves the robot off of the starting line--\\
    public void moveOffLine() {
        //-Moves the robot straight foward at half speed
        /*
        motorDrive.tankDrive(.1, .1);
        Timer.delay(.5);
        motorDrive.tankDrive(.15, .15);
        Timer.delay(0.5);
        motorDrive.tankDrive(.2, .2);
        Timer.delay(0.5);
        motorDrive.tankDrive(.15, .15);
        Timer.delay(0.5);
        motorDrive.tankDrive(.1, .1);
        Timer.delay(0.5);
        motorDrive.tankDrive(0,0);
        */
        
        holdMotorv2(.5, .1, .1);
        holdMotorv2(.5, .15, .15);
        holdMotorv2(.5, .2, .2);
        holdMotorv2(.5, .15, .15);
        holdMotorv2(.5, .1, .1);
        motorDrive.tankDrive(0,0);
        //-Rotates clockwise, no foward motion
        /*
        motorDrive.tankDrive(.5, -.5);
        Timer.delay(4);
        motorDrive.tankDrive(0, 0);
        */
    }

    public void holdMotor(double seconds, double leftSpeed, double rightSpeed) {
        for(int x=0;x<seconds;x+=.001) {
            motorDrive.tankDrive(leftSpeed, rightSpeed);
            Timer.delay(.001);
        }
    }

    public void holdMotorv2(double seconds, double leftSpeed, double rightSpeed) {
        Timer timer = new Timer();
        timer.start();
        while(timer.get()<seconds) {
            motorDrive.tankDrive(leftSpeed, -rightSpeed);
        }
    }
}
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
        motorDrive.tankDrive(.5, .5);
        Timer.delay(1);
        //-Rotates clockwise, no foward motion
        /*
        motorDrive.tankDrive(.5, -.5);
        Timer.delay(4);
        motorDrive.tankDrive(0, 0);
        */
    }
}
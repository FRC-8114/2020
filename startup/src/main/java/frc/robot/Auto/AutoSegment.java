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
    public void moveOffLine(Timer timer) {
        if(timer.get()<0.5) {
            motorDrive.tankDrive(.2, .2);
        } else if(timer.get()<1) {
            motorDrive.tankDrive(.25, .25);
        } else if(timer.get()<1.5) {
            motorDrive.tankDrive(.3, .3);
        } else if(timer.get()<2) {
            motorDrive.tankDrive(.25, .25);
        } else if(timer.get()<2.5) {
            motorDrive.tankDrive(.2, .2);
        } else {
            motorDrive.stopMotor();
        }
    }
}
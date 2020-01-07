package frc.robot.auto;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.Timer;

public class AutoSegment {
    private DifferentialDrive motorDrive;
    private Timer timer;

    //---------------------------------------------------------------\\
    //--Constructor--\\
    public AutoSegment(DifferentialDrive motorDrive) {
        this.motorDrive = motorDrive;

        timer = new Timer();
    }

    //---------------------------------------------------------------\\
    //--Scores points via shooting balls into the power port--\\
    public void shootPowerCells() {
        
    }

    //---------------------------------------------------------------\\
    //--Moves the robot off of the starting line--\\
    public void moveOffLine() {
        motorDrive.arcadeDrive(.5, 0);
        timer.delay(1);
        motorDrive.arcadeDrive(0,0);
    }
}
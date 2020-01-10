package frc.robot.Auto;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Encoder;


public class AutoSegment {
    private DifferentialDrive motorDrive;
    private Encoder leftEncoder, rightEncoder;
    //---------------------------------------------------------------\\
    //--Constructor--\\
    public AutoSegment(DifferentialDrive motorDrive, Encoder leftEncoder, Encoder rightEncoder) {
        this.leftEncoder = leftEncoder;
        this.rightEncoder = rightEncoder;
        this.motorDrive = motorDrive;

    }

    //---------------------------------------------------------------\\
    //--Scores points via shooting balls into the power port--\\
    public void shootPowerCells() {
        
    }

    //---------------------------------------------------------------\\
    //--Moves the robot off of the starting line--\\
    public void moveOffLine(Timer timer) {
        if(timer.get()<.5) {
            motorDrive.tankDrive(.2, .2, false);
        } else if(timer.get()<1) {
            motorDrive.tankDrive(.4, .4, false);
        } else if(timer.get()<2) {
            motorDrive.tankDrive(.8, .8, false);
        } else if(timer.get()<2.5) {
            motorDrive.tankDrive(.4, .4, false);
        } else if(timer.get()<3) {
            motorDrive.tankDrive(.2, .2, false);
        } else {
            motorDrive.stopMotor();
        }
        System.out.println("Time: "+ timer.get());
    }
}
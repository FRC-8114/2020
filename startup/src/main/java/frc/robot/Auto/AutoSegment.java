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
    public void moveOffLine(double time) {
        double second_total = 4;
        if(time<=(0.25*second_total)) {
            motorDrive.tankDrive(time*.8, time*.8, false);
        } else if(time<(0.75*second_total)) {
            motorDrive.tankDrive(.8, .8, false);
        } else if(time<second_total) {
            motorDrive.tankDrive((4-time)*.8, (4-time)*.8, false);
        } else {
            motorDrive.stopMotor();
        }
    }
}
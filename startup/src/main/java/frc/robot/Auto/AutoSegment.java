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
        motorDrive.arcadeDrive(.5, 0);
        Timer.delay(1);
        //-Rotates clockwise, no foward motion
        motorDrive.arcadeDrive(0, .5);
        Timer.delay(4);
        //-Move motor of the arm up, possibly until camera detects color at spot
        
        /** It's comment code time
         * 
         * where camera is edu.wpi.cscore.UsbCamera
         * while(!camera.isColorPositionCorrect()) {
         *  armDrive.drive(.5);
         *  Timer.delay(0.5);
         * }
         * shootDrive.drive(1.0);
         * Timer.delay(4);
         */
    }
}
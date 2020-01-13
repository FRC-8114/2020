package frc.robot.subsystems;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveSystem extends SubsystemBase {
    private DifferentialDrive motorDrive;
    private Joystick controller;

    int i = -1;

    //----------------------------------------------------------------------------\\
    //--Constructor--\\
    public DriveSystem(DifferentialDrive motorDrive, Joystick controller) {
        this.controller = controller;
        this.motorDrive = motorDrive;
    }

    //----------------------------------------------------------------------------\\
    //--Periodic--\\
    public void periodic() {
        /**
         * The stick is mapped like this:
         * Y AXIS IS UP AND DOWN
         * X AXIS IS LEFT AND RIGHT
         *     -1.0
         * -1.0     1.0
         *      1.0
         * Invert just the getY() to get movement right
         * Joystick has drift, use stepping by 100 steps ie
         *    1.0 ~ 1.0
         *    0.938 ~ 0.93
         *    0.006 ~ 0.0
        */
        motorDrive.tankDrive(i*Math.floor(controller.getY(Hand.kLeft)*100)/100*.75,
        i*Math.floor(controller.getY(Hand.kRight)*100)/100*.75, false);
    }

    //----------------------------------------------------------------------------\\
    //--Reverse for command--\\
    public void inverse() {
        i*=-1;
    }
}
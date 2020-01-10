package frc.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.GenericHID.Hand;

public class DriveSystem extends SubsystemBase {
    private DifferentialDrive motorDrive;

    private XboxController controller;

    //----------------------------------------------------------------------------\\
    //--Constructor--\\
    public DriveSystem(DifferentialDrive motorDrive, XboxController controller) {
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
        motorDrive.tankDrive(-Math.floor(controller.getY(Hand.kLeft)*100)/100*.75,
        -Math.floor(controller.getY(Hand.kRight)*100)/100*.75, false);
    }
}
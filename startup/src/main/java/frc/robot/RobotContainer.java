package frc.robot;

import edu.wpi.first.wpilibj.XboxController;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import frc.robot.subsystems.DriveSystem;

public class RobotContainer {
    private final DriveSystem driveSystem;

    public RobotContainer(DifferentialDrive motorDrive, XboxController controller) {
        driveSystem = new DriveSystem(motorDrive, controller);
    }
}
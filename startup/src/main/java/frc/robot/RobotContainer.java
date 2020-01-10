package frc.robot;

import edu.wpi.first.wpilibj.XboxController;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import frc.robot.subsystems.DriveSystem;

import frc.robot.commands.DriveCommand;

public class RobotContainer {
    private final XboxController controller;

    private final DriveSystem driveSystem;

    public RobotContainer(DifferentialDrive motorDrive, XboxController controller) {
        driveSystem = new DriveSystem(motorDrive, controller);
    
        this.controller = controller;
        configureButtonBindings();
    }

    private void configureButtonBindings() {
        new JoystickButton(DriveCommand, controller.Button.a.value).whenActive(new DriveCommand(driveSystem)));
    }
}
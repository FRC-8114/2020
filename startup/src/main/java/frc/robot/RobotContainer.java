package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.subsystems.DriveSystem;
import frc.robot.commands.DriveCommand;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class RobotContainer {
    private Joystick controller;
    private DriveSystem driveSystem;
    private JoystickButton button;

    public RobotContainer(DifferentialDrive motorDrive, Joystick controller) {
        driveSystem = new DriveSystem(motorDrive, controller);
    
        this.controller = controller;
        configureButtonBindings();
    }

    private void initButtons() {
        button = new JoystickButton(controller, 1);
    }

    private void configureButtonBindings() {
        initButtons();

        button.whenPressed(new DriveCommand(driveSystem));
    }
}
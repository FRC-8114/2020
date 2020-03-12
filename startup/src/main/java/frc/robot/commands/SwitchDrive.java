package frc.robot.commands;

import frc.robot.subsystems.DriveTrain;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class SwitchDrive extends CommandBase {
    private DriveTrain driveTrain;
    private XboxController controller;
    private double var;

    public SwitchDrive(XboxController controller, DriveTrain driveTrain) {
        this.driveTrain = driveTrain;
        this.controller = controller;

        addRequirements(driveTrain);
    }

    public void initialize() {
        var = -1.0 * driveTrain.getSpeedModifier();
        driveTrain.speedModifier = var;
    }

    public void execute() {
    }

    public boolean isFinished() {
        return true;
    }
}
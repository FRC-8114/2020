package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.DriveSystem;

public class DriveCommand extends CommandBase {
    private final DriveSystem driveSystem;
    
    public DriveCommand(DriveSystem driveSystem) {
        this.driveSystem = driveSystem;
    }

    public void initialize() {
        driveSystem.inverse();
    }

    public boolean isFinished() {
        return true;
    }
}
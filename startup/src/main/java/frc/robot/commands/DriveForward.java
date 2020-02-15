package frc.robot.commands;

import frc.robot.subsystems.*;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class DriveForward extends CommandBase {
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    private DriveSystem driveSystem;
    private double speed;

    public DriveForward(DriveSystem driveSystem, double speed) {
        this.driveSystem = driveSystem;
        this.speed = speed;

        addRequirements(driveSystem);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        driveSystem.drive(speed, speed);
    }

    @Override
    public void end(boolean b) {
        driveSystem.drive(0, 0);
    }

    public boolean isFinished() {
        return true;
    }
}
package frc.robot.commands;

import frc.robot.subsystems.*;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class DirectedMove extends CommandBase {
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    private DriveSystem driveSystem;
    private OdometrySubsystem odometrySubsystem;
    private double speed, length;

    public DirectedMove(DriveSystem driveSystem, OdometrySubsystem odometrySubsystem,
                        double speed, double length) {
        this.driveSystem = driveSystem;
        this.odometrySubsystem = odometrySubsystem;
        this.speed = speed;
        this.length = length;

        addRequirements(driveSystem);
        addRequirements(odometrySubsystem);
    }

    @Override
    public void initialize() {
        odometrySubsystem.resetDriveEncoders(); //reset current distance
    }

    @Override
    public void execute() {
        driveSystem.drive(speed, speed);
    }

    @Override
    public void end(boolean b) {
        driveSystem.drive(-speed, -speed);
        driveSystem.drive(0, 0);
    }

    public boolean isFinished() {
        if(odometrySubsystem.getDriveDistance() > length)
            return true;
        return false;
    }
}
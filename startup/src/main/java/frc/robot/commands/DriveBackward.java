package frc.robot.commands;

import frc.robot.subsystems.DriveTrain;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class DriveBackward extends CommandBase {
    private DriveTrain driveTrain;
    private double distance;

    public DriveBackward(DriveTrain driveTrain, double distance) {
        this.driveTrain = driveTrain;
        this.distance = distance;
    }
}
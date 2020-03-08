package frc.robot.commands;

import frc.robot.subsystems.DriveTrain;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class DriveTurnLeft extends CommandBase {
    private DriveTrain driveTrain;
    private final double distance, speed;

    public DriveTurnLeft(DriveTrain driveTrain, double distance, double speed) {
        this.driveTrain = driveTrain;
        this.distance = distance;
        this.speed = speed;
        driveTrain.resetEncoders();

        addRequirements(driveTrain);
    }

    public void initialize() {
        driveTrain.resetEncoders();
    }

    public void execute() {
        driveTrain.turnLeft(speed, speed);
    }

    public boolean isFinished() {
        if(driveTrain.getLeftEncoderDistance() >= distance && driveTrain.getRightEncoderDistance() >= distance) {
            return true;
        }
        return false;
    }
}
package frc.robot.commands;

import frc.robot.subsystems.DriveTrain;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class DriveBackward extends CommandBase {
    private DriveTrain driveTrain;
    private final double distance, speed;

    public DriveBackward(DriveTrain driveTrain, double distance, double speed) {
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
        /* if (driveTrain.getLeftEncoderDistance() == 0)
            driveTrain.driveBackward(.1*distance);
        if(distance/4 < 1) {
            if(driveTrain.getLeftEncoderDistance() < distance*.25) {speed
                driveTrain.driveBackward();
            } else if(driveTrain.getLeftEncoderDistance() < distance*.75) {
                driveTrain.driveBackward(speed);
            } else {
                driveTrain.driveBackward(((timer.get()-time*.75)/(time*.25))*speed);
            }
        }

        else {
        } */

        driveTrain.driveBackward(speed);
    }

    public boolean isFinished() {
        if(driveTrain.getLeftEncoderDistance() >= distance && driveTrain.getRightEncoderDistance() >= distance) {
            return true;
        }
        return false;
    }
}
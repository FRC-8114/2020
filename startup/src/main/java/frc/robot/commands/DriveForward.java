package frc.robot.commands;

import frc.robot.subsystems.DriveTrain;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class DriveForward extends CommandBase {
    private DriveTrain driveTrain;
    private final double distance;

    public DriveForward(DriveTrain driveTrain, double distance) {
        this.driveTrain = driveTrain;
        this.distance = distance;
        driveTrain.resetEncoders();

        addRequirements(driveTrain);
    }

    public void execute() {
        if(distance*.25<1) {
            if(driveTrain.getRightEncoderDistance() < distance*.25) {
                driveTrain.driveForward((driveTrain.getRightEncoderDistance())/(distance*.25)*.8);
            } else if (driveTrain.getRightEncoderDistance() < distance*.75) {
                driveTrain.driveForward(.8);
            } else {
                driveTrain.driveForward((driveTrain.getRightEncoderDistance()-(distance*.75))/(distance-(distance*.75))*.8);
            }
        } else {
            if(driveTrain.getRightEncoderDistance() < 1) {
                driveTrain.driveForward(driveTrain.getRightEncoderDistance()*.8);
            } else if (driveTrain.getRightEncoderDistance() < distance-1) {
                driveTrain.driveForward(.8);
            } else {
                driveTrain.driveForward((driveTrain.getRightEncoderDistance()-(driveTrain.getRightEncoderDistance()-1))*.8);
            }
        }
    }

    public boolean isFinished() {
        if(driveTrain.getRightEncoderDistance() >= distance) {
            return true;
        }
        return false;
    }
}
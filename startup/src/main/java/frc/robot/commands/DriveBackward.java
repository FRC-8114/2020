package frc.robot.commands;

import frc.robot.subsystems.DriveTrain;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class DriveBackward extends CommandBase {
    private DriveTrain driveTrain;
    private final double distance;

    public DriveBackward(DriveTrain driveTrain, double distance) {
        this.driveTrain = driveTrain;
        this.distance = distance;
        driveTrain.resetEncoders();

        addRequirements(driveTrain);
    }

    public void execute() {
        if(distance*.25<1) {
            System.out.print("Distance less than 25");
            if(driveTrain.getRightEncoderDistance() > -distance*.25) {
                driveTrain.driveBackward(-(driveTrain.getRightEncoderDistance())/(distance*.25)*.8);
            } else if (driveTrain.getRightEncoderDistance() > -distance*.75) {
                driveTrain.driveBackward(-.8);
            } else {
                driveTrain.driveBackward(-(driveTrain.getRightEncoderDistance()-(distance*.75))/(distance-(distance*.75))*.8);
            }
        } else {
            if(driveTrain.getRightEncoderDistance() > -1) {
                driveTrain.driveBackward(-driveTrain.getRightEncoderDistance()*.8);
            } else if (driveTrain.getRightEncoderDistance() > -distance-1) {
                driveTrain.driveBackward(-.8);
            } else {
                driveTrain.driveBackward(-(driveTrain.getRightEncoderDistance()-(driveTrain.getRightEncoderDistance()-1))*.8);
            }
        }
    }

    public boolean isFinished() {
        if(driveTrain.getRightEncoderDistance() <= -distance) {
            System.out.println("Finished");
            return true;
        }
        return false;
    }
}
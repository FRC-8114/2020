package frc.robot.commands;

import frc.robot.subsystems.DriveTrain;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class DriveTurnLeft extends CommandBase {
    private DriveTrain driveTrain;
    private final double distance;

    public DriveTurnLeft(DriveTrain driveTrain, double distance) {
        this.driveTrain = driveTrain;
        this.distance = distance;
        driveTrain.resetEncoders();

        addRequirements(driveTrain);
    }

    public void execute() {
        if(distance*.25<1) {
            if(driveTrain.getLeftEncoderDistance() < distance*.25) {
                driveTrain.turnLeft((driveTrain.getLeftEncoderDistance())/(distance*.25)*.8, (driveTrain.getLeftEncoderDistance())/(distance*.25)*.8);
            } else if (driveTrain.getLeftEncoderDistance() < distance*.75) {
                driveTrain.turnLeft(.8, .8);
            } else {
                driveTrain.turnLeft((driveTrain.getLeftEncoderDistance()-(distance*.75))/(distance-(distance*.75))*.8, (driveTrain.getLeftEncoderDistance()-(distance*.75))/(distance-(distance*.75))*.8);
            }
        } else {
            if(driveTrain.getLeftEncoderDistance() < 1) {
                driveTrain.turnLeft(driveTrain.getLeftEncoderDistance()*.8, driveTrain.getLeftEncoderDistance()*.8);
            } else if (driveTrain.getLeftEncoderDistance() < distance-1) {
                driveTrain.turnLeft(.8, .8);
            } else {
                driveTrain.turnLeft((driveTrain.getLeftEncoderDistance()-(driveTrain.getLeftEncoderDistance()-1))*.8, (driveTrain.getLeftEncoderDistance()-(driveTrain.getLeftEncoderDistance()-1))*.8);
            }
        }
    }

    public boolean isFinished() {
        if(driveTrain.getLeftEncoderDistance() >= distance) {
            return true;
        }
        return false;
    }
}
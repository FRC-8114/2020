package frc.robot.commands;

import frc.robot.subsystems.DriveTrain;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class DriveForward extends CommandBase {
    private DriveTrain driveTrain;
    private final double distance, speed;

    public DriveForward(DriveTrain driveTrain, double distance, double speed) {
        this.driveTrain = driveTrain;
        this.distance = distance;
        this.speed = speed;
        driveTrain.resetEncoders();

        addRequirements(driveTrain);
    }

    public void execute() {
        // If a quarter of the desired distance is less than 1 meter
        if(distance*.25<1) {
            // If the driven distance is less than a quarter of the desired distance
            if(driveTrain.getRightEncoderDistance() < distance*.25) {
                // Drives forward at a speed of the driven distance divided by a quarter of the desired distance, all multiplied by speed
                driveTrain.driveForward((driveTrain.getRightEncoderDistance())/(distance*.25)*speed);
            }
            // Else if the driven distance is less than three quarters of the desired distance
            else if (driveTrain.getRightEncoderDistance() < distance*.75) {
                // Drives forward at speed
                driveTrain.driveForward(speed);
            }
            // Else
            else {
                // Drives forward at a speed of the driven distance minus three quarters of the desired distance, all over one quarter of the desired distance, times speed
                driveTrain.driveForward((driveTrain.getRightEncoderDistance()-(distance*.75))/(distance-(distance*.75))*speed);
            }
        }

        // Else
        else {
            // If the driven distance is less than 1
            if(driveTrain.getRightEncoderDistance() < 1) {
                // Drives forward at a speed of driven distance * speed
                driveTrain.driveForward(driveTrain.getRightEncoderDistance()*speed);
            }
            // Else if the driven distance is less than distance - 1
            else if (driveTrain.getRightEncoderDistance() < distance-1) {
                driveTrain.driveForward(speed);
            }
            else {
                driveTrain.driveForward((driveTrain.getRightEncoderDistance()-(driveTrain.getRightEncoderDistance()-1))*speed);
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
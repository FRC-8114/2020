package frc.robot.autonomous_groups;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.*;
import frc.robot.commands.*;

public class MoveAllyAndShoot extends SequentialCommandGroup {
    public MoveAllyAndShoot(DriveTrain drive, Shooter shooter, Intake intake, Index index) {
        addCommands(
            // Drives Forward, Pushing Ally
            new DriveForward(drive, 1.5),

            // Drives Backward to Original Spot
            new DriveBackward(drive, 1.5),

            // Begins Shooter
            new ShooterForward(shooter, .8),

            // Waits
            new Wait(3),
            
            // Begins Intake
            new IndexForward(index, .8),

            // Waits
            new Wait(2),

            // Stops shooter
            new ShooterStop(shooter),

            // Stops index
            new IndexStop(index)
        );
      }
}
package frc.robot.autonomous_groups;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.*;
import frc.robot.commands.*;

public class ShootAndMove extends SequentialCommandGroup {
    public ShootAndMove(DriveTrain drive, Shooter shooter, Intake intake, Index index) {
        System.out.println("Command init");
        addCommands(
            // Starts the spin-up of the shooter
            new ShooterForward(shooter, .8),

            // Delays a certain number of seconds to allow for spin-up
            new Wait(3),

            // Begins the intake
            new IndexForward(index, .8),

            // Delays a certain number of seconds to allow for balls to exit shooter
            new Wait(2),

            // Stops shooter
            new ShooterStop(shooter),

            // Stops index
            new IndexStop(index),

            // Drives backward
            new DriveBackward(drive, 1)
        );
      }
}
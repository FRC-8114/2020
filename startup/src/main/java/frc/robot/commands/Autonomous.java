package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.*;

public class Autonomous extends SequentialCommandGroup {
    public Autonomous(DriveSystem driveSystem, OdometrySubsystem odometrySystem, ShooterSystem shooterSystem, IntakeSystem intakeSystem, WheelOfMisfortuneSystem wheelOfMisfortuneSystem) {
        addCommands(
            // Waits a number of seconds
            new Wait(0),
            // Shoots for the high goal
            new AutoShoot(shooterSystem, intakeSystem, .7/*, 3*/),
            // Moves away from the initiation line
            new TimedMove(driveSystem, /*odometrySystem,*/ 1.5, -.75, -.75)
        );
      }
}
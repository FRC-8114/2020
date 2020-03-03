package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.*;

public class Autonomous extends SequentialCommandGroup {
    public Autonomous(DriveSystem driveSystem, WheelOfMisfortuneSystem wheelOfMisfortuneSystem, OdometrySubsystem odometrySystem, ShooterSystem shooterSystem, IntakeSystem intakeSystem) {
        addCommands(
            // Shoots for the high goal
            new AutoShoot(shooterSystem, intakeSystem, .7),
            // Moves away from the initiation line
            new SmoothMove(driveSystem, odometrySystem, .1)
        );
      }
}
package frc.robot.commands.commandGroups;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.*;
import frc.robot.commands.*;

public class Autonomous extends SequentialCommandGroup {
    public Autonomous(DriveSystem driveSystem, OdometrySubsystem odometrySystem, ShooterSystem shooterSystem, IntakeSystem intakeSystem) {
        System.out.println("Autonomous initialized");
        addCommands(
            // Shoots for the high goal
            new AutoShoot(shooterSystem, intakeSystem, .8, 3),
            // Moves away from the initiation line
            new TimedMove(driveSystem, /*odometrySystem,*/ 0.8, -.75, -.75)
        );
      }
}
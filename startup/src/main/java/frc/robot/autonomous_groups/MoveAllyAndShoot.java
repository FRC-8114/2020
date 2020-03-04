package frc.robot.autonomous_groups;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.*;
import frc.robot.commands.*;

public class MoveAllyAndShoot extends SequentialCommandGroup {
    public MoveAllyAndShoot(DriveTrain drive, Shooter shooter, Intake intake) {
        addCommands(
            new ShooterForward(shooter, .8)
        );
      }
}
package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClimberSystem;

public class ClimberRaise extends CommandBase {
    private ClimberSystem climber;

    public ClimberRaise(ClimberSystem climber) {
        this.climber = climber;
    }

    public void execute() {
        climber.extendClimber(1);
    }

    public boolean isFinished() {
        return true;
    }
}